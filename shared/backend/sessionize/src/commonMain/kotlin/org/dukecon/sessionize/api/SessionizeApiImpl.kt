package org.dukecon.sessionize.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import org.dukecon.sessionize.jsondata.Days
import org.dukecon.sessionize.jsondata.Room
import org.dukecon.sessionize.jsondata.Session
import org.dukecon.sessionize.jsondata.Speaker

class SessionizeApiImpl(override val conferenceInstanceId: String) : SessionizeApi {

  override suspend fun getRooms(): List<Room> =
      client.get("https://sessionize.com/api/v2/$conferenceInstanceId/view/gridtable")

  private val nonStrictJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
  }

  private val client = HttpClient {
    install(JsonFeature) {
      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
      }
      serializer = KotlinxSerializer(nonStrictJson)
    }
  }

  override suspend fun getSpeakers(): List<Speaker> =
      client.get("/api/v2/$conferenceInstanceId/view/speakers")

  override suspend fun getSessions(): List<Session> {
    val roomList: List<Days> =
        client.get("https://sessionize.com/api/v2/$conferenceInstanceId/view/gridtable")
    return roomList.flatMap { days -> days.rooms.flatMap { it.sessions } }
  }
}
