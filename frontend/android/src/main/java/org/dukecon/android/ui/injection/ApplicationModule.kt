package org.dukecon.android.ui.injection

/**
 * Module used to provide dependencies at an application-level.

@Module
open class ApplicationModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun providetwitterLinkMapper(): TwitterLinks {
        return TwitterLinks()
    }

    @Provides
    fun provideSpeakerMapper(): org.dukecon.presentation.mapper.SpeakerMapper {
        return org.dukecon.presentation.mapper.SpeakerMapper()
    }

    @Provides
    fun provideSpeakerDetailMapper(twitterLinks: TwitterLinks): org.dukecon.presentation.mapper.SpeakerDetailMapper {
        return org.dukecon.presentation.mapper.SpeakerDetailMapper(twitterLinks)
    }

    @Provides
    internal fun providePreEventMapper(speakersMapper: org.dukecon.presentation.mapper.SpeakerMapper): org.dukecon.presentation.mapper.EventMapper {
        return org.dukecon.presentation.mapper.EventMapper(speakersMapper)
    }


    class DO_NOT_VERIFY_IMP : javax.net.ssl.HostnameVerifier {
        override fun verify(p0: String?, p1: javax.net.ssl.SSLSession?): Boolean {
            return true
        }
    }

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

    @Singleton
    @Provides
    fun provideNonCachedOkHttpClient(application: Application): OkHttpClient {

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

        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { it ->
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

    @Provides
    fun provideAuthManager(dukeconAuthManager: DummyDukeconAuthManager): AuthManager {
        return dukeconAuthManager
    }


    @Provides
    @Singleton
    internal fun provideConferenceRepository(conferenceConfiguration: ConferenceConfiguration, okHttpClient: OkHttpClient): ConferenceRepository =
            RepositoryFactory.createConferenceRepository(conferenceConfiguration, okHttpClient)

    @Provides
    fun providesTokensStorage(settings: SettingsTokenStorage): TokensStorage {
        return settings
    }
}
 */

