package org.dukecon.android.ioc

//import org.dukecon.android.ui.features.login.DummyDukeconAuthManager
import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.dukecon.android.ui.configuration.RepositoryFactory
import org.dukecon.cache.storage.ApplicationContext
import org.dukecon.cache.storage.ApplicationStorage
import org.dukecon.cache.storage.applicationStorageFactory
import org.dukecon.core.IoCProvider
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.time.CurrentDataTimeProvider
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit

object IoCRegisterRemoteClient {


    class XtmImp : javax.net.ssl.X509TrustManager {
        override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            val x509Certificates: Array<X509Certificate> = arrayOf()
            return x509Certificates
        }
    }

    class DO_NOT_VERIFY_IMP : javax.net.ssl.HostnameVerifier {
        override fun verify(p0: String?, p1: javax.net.ssl.SSLSession?): Boolean {
            return true
        }
    }

    private fun provideNonCachedOkHttpClient(application: Application): OkHttpClient {

        val cacheSize = 10 * 1024 * 1024L // 10 MB
        val cache = Cache(application.getCacheDir(), cacheSize)

        val xtm = XtmImp()
        val sslContext = javax.net.ssl.SSLContext.getInstance("SSL")
        try {
            sslContext.init(null, arrayOf<javax.net.ssl.TrustManager>(xtm), java.security.SecureRandom())
        } catch (e: java.security.NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: java.security.KeyManagementException) {
            e.printStackTrace()
        }

        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                //LOGGER.debug("XXX = $message")
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .sslSocketFactory(sslContext.getSocketFactory(), xtm)
            .hostnameVerifier(DO_NOT_VERIFY_IMP())
            .build()
    }


    fun registerConferenceApi(context: Application) {
//        IoCProvider.registerType(AuthManager::class, DummyDukeconAuthManager())
        val conferenceConfiguration = IoCProvider.get<ConferenceConfiguration>()
        val okHttpClient = provideNonCachedOkHttpClient(context)
        val currentTimeProvider = IoCProvider.get<org.dukecon.domain.features.time.CurrentTimeProvider>()
        IoCProvider.registerType(
            ConferenceRepository::class,
            RepositoryFactory.createConferenceRepository(
                conferenceConfiguration,
                okHttpClient,
                object : CurrentDataTimeProvider {
                    override fun currentTimeMillis(): Long = currentTimeProvider.currentTimeMillis()
                },
                applicationStorageFactory(context = ApplicationContext(context))
            )
        )
    }
}
