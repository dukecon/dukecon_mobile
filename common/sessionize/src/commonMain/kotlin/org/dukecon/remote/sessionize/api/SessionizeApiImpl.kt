package org.dukecon.remote.sessionize.api

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.dukecon.remote.sessionize.jsondata.Days
import org.dukecon.remote.sessionize.jsondata.Room
import org.dukecon.remote.sessionize.jsondata.Session
import org.dukecon.remote.sessionize.jsondata.Speaker
import kotlin.native.concurrent.ThreadLocal

/*
    private val INSTANCE_ID = "zs3eoop8"
    private val SPONSOR_INSTANCE_ID = "zs3eoop8"

 */

@ThreadLocal
class SessionizeApiImpl(override val conferenceInstanceId: String) : SessionizeApi {
    override suspend fun getRooms(): List<Room> =
            parseRoomsFromDays(client.get<String> {
                sessionize("/api/v2/$conferenceInstanceId/view/gridtable")
            })


    private val client = HttpClient {
        install(ExpectSuccess)
    }

    override suspend fun getSpeakers(): List<Speaker> =
            primeSpeakers(client.get<String> {
                sessionize("/api/v2/$conferenceInstanceId/view/speakers")
            })

    override suspend fun getSessions(): List<Session> =
            parseSessionsFromDays(client.get<String> {
                sessionize("/api/v2/$conferenceInstanceId/view/gridtable")
            })

    private fun HttpRequestBuilder.sessionize(path: String) {
        url {
            takeFrom("https://sessionize.com")
            encodedPath = path
        }
    }

    private fun primeSpeakers(speakerJson: String): List<Speaker> =
            Json.nonstrict.parse(Speaker.serializer().list, speakerJson)//DefaultData.parseSpeakers(speakerJson)

    private fun parseSessionsFromDays(scheduleJson: String): List<Session> {
        val days = Json.nonstrict.parse(Days.serializer().list, scheduleJson)
        val sessions = mutableListOf<Session>()

        days.forEach { day ->
            day.rooms.forEach { room ->
                sessions.addAll(room.sessions)
            }
        }

        return sessions
    }

    private fun parseRoomsFromDays(scheduleJson: String): List<Room> {
        val days = Json.nonstrict.parse(Days.serializer().list, scheduleJson)
        val rooms = mutableListOf<Room>()

        days.forEach { day ->
            day.rooms.forEach { room ->
                rooms.add(room)
            }
        }
        return rooms.distinctBy { it.id }
    }

}
