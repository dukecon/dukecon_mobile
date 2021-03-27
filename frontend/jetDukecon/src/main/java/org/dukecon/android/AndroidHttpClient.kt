package org.dukecon.android

import android.app.Application
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.util.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit

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

private fun getConfig(okHttpClientInstance: OkHttpClient): OkHttpConfig {
    return OkHttpConfig().apply {
        config {
            followRedirects(true)
        }
        preconfigured = okHttpClientInstance
    }
}

@InternalAPI
fun createUnsecureOkHttpClient(application: Application): HttpClientEngine {
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
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .cache(cache)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .sslSocketFactory(sslContext.getSocketFactory(), xtm)
        .hostnameVerifier(DO_NOT_VERIFY_IMP())
        .build()
    return OkHttpEngine(getConfig(okHttpClient))
}
