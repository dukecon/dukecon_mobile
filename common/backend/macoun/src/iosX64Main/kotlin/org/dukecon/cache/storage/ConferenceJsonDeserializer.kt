package org.dukecon.macoun.mapper

import org.dukecon.macoun.api.Conference

actual object ConferenceJsonDeserializer {

    actual fun toConference(jsonStr: String): Conference = Conference()
}