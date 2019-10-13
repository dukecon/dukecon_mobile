package org.dukecon.remote.sessionize.api

import org.dukecon.remote.sessionize.jsondata.Room
import org.dukecon.remote.sessionize.jsondata.Session
import org.dukecon.remote.sessionize.jsondata.Speaker

interface SessionizeApi {
    suspend fun getSpeakers(): List<Speaker>

    suspend fun getSessions(): List<Session>

    suspend fun getRooms(): List<Room>

    val conferenceInstanceId: String

}

