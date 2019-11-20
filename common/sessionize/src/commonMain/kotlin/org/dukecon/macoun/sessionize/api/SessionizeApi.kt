package org.dukecon.macoun.sessionize.api

import org.dukecon.macoun.sessionize.jsondata.Room
import org.dukecon.macoun.sessionize.jsondata.Session
import org.dukecon.macoun.sessionize.jsondata.Speaker

interface SessionizeApi {
    suspend fun getSpeakers(): List<Speaker>

    suspend fun getSessions(): List<Session>

    suspend fun getRooms(): List<Room>

    val conferenceInstanceId: String

}

