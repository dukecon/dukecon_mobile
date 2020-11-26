package org.dukecon.sessionize.store

import io.ktor.util.date.GMTDate
import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceRemote
import org.dukecon.sessionize.api.SessionizeApi
import org.dukecon.sessionize.jsondata.Room
import org.dukecon.sessionize.jsondata.Session
import org.dukecon.sessionize.jsondata.Speaker
import org.dukecon.sessionize.mapper.parseDate

/**
 * Sessionize backed network calls remote service. Makes netowork calls and convert data to RemoteData Store DTO.
 */
class SessionizeConferenceRemotes(
        private val sessionizeApi: SessionizeApi
) : ConferenceRemote {
    override suspend fun getSpeakers(): List<SpeakerEntity> {
        try {
            return sessionizeApi.getSpeakers().map {
                toSpeakerEntity(it)
            }
        } catch (source: Throwable) {
            println(source.toString())
        }
        return emptyList()
    }

    private fun toSpeakerEntity(it: Speaker): SpeakerEntity = SpeakerEntity(
            id = it.id ?:"",
            name = it.fullName,
            title = it.tagLine,
            twitter = it.links.firstOrNull { it.linkType.contains("twitter") }?.url ?: "",
            bio = it.bio,
            website = it.links.firstOrNull { !it.linkType.contains("twitter") }?.url ?: "",
            avatar = it.profilePicture ?: ""
    )

    override suspend fun getRooms(): List<RoomEntity> {
        try {
            return sessionizeApi.getRooms().map {
                toRoomEntity(it)
            }
        } catch (source: Throwable) {
            println(source.toString())
        }
        return emptyList()
    }

    private fun toRoomEntity(it: Room): RoomEntity = RoomEntity(
            id = it.id,
            name = it.name
    )

    override fun submitFeedback(feedback: FeedbackEntity) {
    }

    override suspend fun getMetaData(): MetaDataEntity = MetaDataEntity(
            id = sessionizeApi.conferenceInstanceId,
            audiences = emptyList(),
            eventTypes = emptyList(),
            languages = emptyList(),
            defaultLanguage = LanguageEntity("", "", 0, emptyMap(), ""),
            tracks = emptyList(),
            locations = getRooms().map { toLocationsEntity(it) },
            defaultIcon = ""
    )

    private fun toLocationsEntity(it: RoomEntity): LocationsEntity = LocationsEntity(
            id = it.id,
            order = 0,
            names = mapOf(Pair("de", it.name)),
            icon = "",
            capacity = 0
    )

    override fun getFavorites(): List<FavoriteEntity> {
        return emptyList()
    }

    override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
        return emptyList()
    }

    override suspend fun getEvents(): List<EventEntity> {
        try {
            val rooms = sessionizeApi.getRooms()
            return sessionizeApi.getSessions().map {
                toEventEntity(it, rooms)
            }
        } catch (source: Throwable) {
            println(source.toString())
        }
        return emptyList()
    }

    private fun toEventEntity(it: Session, rooms: List<Room>): EventEntity = EventEntity(
            id = it.id ?: "",
            title = it.title ?: "",
            abstractText = it.descriptionText ?: "",
            startTime = it.startsAt?.parseDate() ?: GMTDate.START,
            endTime = it.endsAt?.parseDate() ?: GMTDate.START,
            speakerIds = it.speakers.map { it.id  ?:""},
            locationId = rooms.firstOrNull { room -> room.name == it.room }?.id ?: it.room,
            languageId = "",
            trackId = "",
            audienceId = "",
            eventTypeId = "",
            demo = false,
            simultan = false,
            veryPopular = false,
            fullyBooked = false,
            numberOfFavorites = 0)
}
