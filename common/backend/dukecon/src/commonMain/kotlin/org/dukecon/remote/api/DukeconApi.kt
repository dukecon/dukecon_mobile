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

/**
 * use default http engine by default
 */
class DukeconApi(private val endpoint: String, val conference: String, engine: HttpClientEngine? = null) {

    private val client = engine?.let {
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

    /**
     * returns list of conferences
     *
     * @return successful operation
     */
    internal suspend fun getAllConferences(
    ): List<Conference> {
        return client.get<List<Conference>>("$endpoint/conferences") {
        }
    }

    /**
     * returns conference
     *
     * @param id null
     *
     * @return successful operation
     */
    internal suspend fun getConference(
            id: String // PATH
    ): Conference {
        return client.get<Conference>("$endpoint/conferences/$id") {

        }
    }

    /**
     * returns list of conference events
     *
     * @param id null
     *
     * @return successful operation
     */
    internal suspend fun getEvents(
            id: String // PATH
    ): List<Event> {
        return client.get<List<Event>>("$endpoint/conferences/$id/events") {
        }
    }

    /**
     * returns list of conference meta data
     *
     * @param id null
     *
     * @return successful operation
     */
    internal suspend fun getMeta(
            id: String // PATH
    ): MetaData {
        return client.get<MetaData>("$endpoint/conferences/$id/metadata") {
        }
    }

    /**
     * returns list of conference speakers
     *
     * @param id null
     *
     * @return successful operation
     */
    internal suspend fun getSpeakers(
            id: String // PATH
    ): List<Speaker> {
        return client.get<List<Speaker>>("$endpoint/conferences/$id/speakers") {
        }
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
    internal suspend fun updateFeedback(
            id: String, // PATH
            sessionId: String, // PATH
            body: Feedback // BODY
    ): String {
        return client.put<String>("$endpoint/feedback/event/$id/$sessionId") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Conference styles
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getConferenceStyles(
            id: String // PATH
    ): String {
        return client.get<String>("$endpoint/conferences/$id/styles.css") {
        }
    }

    /**
     * returns keycloak setup
     *
     * @param id null
     *
     * @return successful operation
     */
    internal suspend fun getKeyCloak(
            id: String // PATH
    ): Keycloak {
        return client.get<Keycloak>("$endpoint/conferences/$id/keycloak.json") {
        }
    }

    private fun HttpRequestBuilder.apiUrl(path: String, userId: String? = null) {
        if (userId != null) {
            header(HttpHeaders.Authorization, "Bearer $userId")
        }
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(endpoint)
            encodedPath = path
        }
    }

}