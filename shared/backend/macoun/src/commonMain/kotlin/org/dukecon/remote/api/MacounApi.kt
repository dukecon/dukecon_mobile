package org.dukecon.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import org.dukecon.remote.models.*
import org.dukecon.remote.models.Conference
import org.dukecon.remote.models.Event
import org.dukecon.remote.models.Feedback
import org.dukecon.remote.models.MetaData
import org.dukecon.remote.models.Speaker

class MacounApi(
    private val endpoint: String,
    val conference: String,
    engine: HttpClientEngine? = null
) {

    private val client = engine?.let {
        HttpClient(engine) {
            config()
        }
    } ?: HttpClient() {
        config()
    }

    private fun HttpClientConfig<*>.config() {
        install(JsonFeature) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
            serializer = KotlinxSerializer(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    }

    suspend fun getFahrplan(): Fahrplan = client.get("$endpoint/$conference")

}