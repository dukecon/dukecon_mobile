package org.dukecon.remote.mapper

import com.google.gson.Gson
import org.dukecon.remote.api.Booking
import org.dukecon.remote.api.Conference
import org.dukecon.remote.api.EmptyObject
import org.dukecon.remote.api.Location
import org.dukecon.remote.api.Speaker
import org.dukecon.remote.api.Talk

actual object ConferenceJsonDeserializer {

    val gson = Gson()
    actual fun toConference(jsonStr: String): Conference {
        val jsonConference = gson.fromJson(jsonStr, ConferenceJson::class.java)
        return Conference(
                dtd = jsonConference.dtd,
                conference = jsonConference.conference,
                year = jsonConference.year,
                breakingnews = jsonConference.breakingnews,
                speakers = jsonConference.speakers.map { mapSpeakers(it) },
                talks = jsonConference.talks.map { mapTalk(it) },
                rooms = jsonConference.rooms.map { mapRooms(it) }
        )
    }

    private fun mapTalk(talkJson: TalkJson): Talk {
        return Talk(
                id = talkJson.id,
                title = talkJson.title,
                description = talkJson.description,
                startdate = talkJson.startdate,
                duration = talkJson.duration,
                speaker_id = talkJson.speaker_id,
                room_id = talkJson.room_id,
                category = talkJson.category,
                booking = toBooking(talkJson.booking),
                links = EmptyObject(),
                sidetracked = false
        )
    }

    private fun toBooking(booking: BookingJson): Booking {
        return Booking(from = booking.from,
                to = booking.to,
                seats = booking.seats)

    }

    private fun mapSpeakers(it: SpeakerJson): Speaker {
        return Speaker(
                id = it.id,
                firstname = it.firstname,
                lastname = it.lastname,
                image = it.image,
                twitter = it.twitter,
                description = it.description)

    }

    private fun mapRooms(it: LocationJson): Location {
        return Location(
                id = it.id,
                sort_key = it.sort_key,
                name = it.name,
                seats = it.seats)
    }
}