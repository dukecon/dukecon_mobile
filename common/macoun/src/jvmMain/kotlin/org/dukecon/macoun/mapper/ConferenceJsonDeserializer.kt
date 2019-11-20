package org.dukecon.macoun.mapper

import com.google.gson.Gson
import org.dukecon.macoun.api.Conference

actual object ConferenceJsonDeserializer {

    val gson = Gson()
    actual fun toConference(jsonStr: String): Conference =
            gson.fromJson(jsonStr, Conference::class.java)
}