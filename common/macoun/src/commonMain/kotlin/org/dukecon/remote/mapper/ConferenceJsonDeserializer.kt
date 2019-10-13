package org.dukecon.remote.mapper

import org.dukecon.remote.api.Conference

expect object ConferenceJsonDeserializer {
    fun toConference(jsonStr: String): Conference
}
