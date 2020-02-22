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
import kotlinx.serialization.json.JsonConfiguration
import org.dukecon.network.generated.apis.ConferencesApi

/**
 * use default http engine by default
 */
class DukeconApi(private val endpoint: String, val conference: String, engine: HttpClientEngine? = null) {

    private val httpClient = engine?.let {
        HttpClient(engine) {
            config()
        }
    } ?: HttpClient() {
        config()
    }

    private fun HttpClientConfig<*>.config() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict).apply {
                setMapper(Conference::class, Conference.serializer())
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    private val client = ConferencesApi(endpoint, httpClient, Json(JsonConfiguration.Default))

    /**
     * returns list of conferences
     *
     * @return successful operation
     */
    suspend fun getAllConferences(
    ): List<Conference> {
        TODO("not implemented yet")
    }

    /**
     * returns conference
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getConference(
            id: String // PATH
    ): Conference {
        return client.getConference(id)
    }

    /**
     * returns list of conference events
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getEvents(
            id: String // PATH
    ): List<Event> {
        return client.getEvents(id)
    }

    /**
     * returns list of conference meta data
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getMeta(
            id: String // PATH
    ): MetaData {
        return client.getMeta(id)
    }

    /**
     * returns list of conference speakers
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getSpeakers(
            id: String // PATH
    ): List<Speaker> {
        return client.getSpeakers(id)
    }

    /**
     * submit feedback to talk
     *
     * @param id conferenceId
     * @param sessionId session Id
     * @param body Feedback object that needs to be updated
     *
     * @return OK
     */
    suspend fun updateFeedback(
            id: String, // PATH
            sessionId: String, // PATH
            body: Feedback // BODY
    ): String {
        return client.updateFeedback(id, sessionId, body) as String
    }

    /**
     * returns keycloak setup
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getKeyCloak(
            id: String // PATH
    ): Keycloak {
        return client.getKeyCloak(id)
    }
}