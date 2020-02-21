package org.dukecon.sessionize.api

import org.dukecon.sessionize.jsondata.Room
import org.dukecon.sessionize.jsondata.Session
import org.dukecon.sessionize.jsondata.Speaker

interface SessionizeApi {
    suspend fun getSpeakers(): List<Speaker>

    suspend fun getSessions(): List<Session>

    suspend fun getRooms(): List<Room>

    val conferenceInstanceId: String
}

