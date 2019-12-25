package org.dukecon.macoun.mapper

import org.dukecon.macoun.api.Conference

expect object ConferenceJsonDeserializer {
    fun toConference(jsonStr: String): Conference
}
