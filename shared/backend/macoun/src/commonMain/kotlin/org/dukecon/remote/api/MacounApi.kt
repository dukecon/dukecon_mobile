package org.dukecon.remote.api

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import org.dukecon.remote.models.Fahrplan

class MacounApi(
    private val endpoint: String,
    val conference: String,
    engine: HttpClientEngine? = null
) {

  private val client = engine?.let { HttpClient(engine) { config() } } ?: HttpClient() { config() }

  private fun HttpClientConfig<*>.config() {
    install(JsonFeature) {
      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
      }
      serializer =
          KotlinxSerializer(
              Json {
                isLenient = true
                ignoreUnknownKeys = true
              })
    }
  }

  suspend fun getFahrplan(): Fahrplan = client.get("$endpoint/$conference")
}
