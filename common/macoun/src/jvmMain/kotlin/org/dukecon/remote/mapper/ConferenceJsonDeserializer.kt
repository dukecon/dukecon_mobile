package org.dukecon.remote.mapper

import com.google.gson.Gson
import org.dukecon.remote.api.Conference

actual object ConferenceJsonDeserializer {

    val gson = Gson()
    actual fun toConference(jsonStr: String): Conference = gson.fromJson(jsonStr,  org.dukecon.remote.mapper.ConferenceJson::class.java)

}