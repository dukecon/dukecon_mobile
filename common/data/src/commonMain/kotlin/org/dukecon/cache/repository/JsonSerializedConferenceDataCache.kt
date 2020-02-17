package org.dukecon.cache.repository

import io.ktor.util.date.GMTDate
import org.dukecon.cache.model.*
import org.dukecon.cache.serializer.ConferenceModelJsonSerializer
import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceDataCache
import org.dukecon.time.CurrentDataTimeProvider

class JsonSerializedConferenceDataCache(private val currentTimeProvider: CurrentDataTimeProvider) : ConferenceDataCache {
    private val json = ConferenceModelJsonSerializer(currentTimeProvider)

    private var cachedRooms: List<RoomEntity> = listOf()
    private var cachedEvents: List<EventEntity> = listOf()
    private var cacheSpeakers: List<SpeakerEntity> = listOf()
    private var cacheFavorites: List<FavoriteEntity> = listOf()
    private var cachedMetaData: MetaDataEntity = emptyMetaDataEntity()

    private fun emptyMetaDataEntity(): MetaDataEntity =
            MetaDataEntity(
                    id = "",
                    audiences = emptyList(),
                    eventTypes = emptyList(),
                    languages = emptyList(),
                    defaultLanguage = LanguageEntity("", "", 0, emptyMap(), ""),
                    tracks = emptyList(),
                    locations = emptyList(),
                    defaultIcon = ""
            )

    init {
        if (json.lastUpadte() ?: 0 > 0) {
            json.run {
                cachedRooms = conference.rooms.map {
                    toRooms(it)
                }
                cachedEvents = conference.sessions.map {
                    toEvent(it)
                }
                cacheSpeakers = conference.speakers.map {
                    toSpeaker(it)
                }
                cacheFavorites = conference.favorites.map {
                    toFavorite(it)
                }
                cachedMetaData = toMetadata(conference.metaData)
            }
        }
    }

    private fun toMetadata(it: MetaDataModel): MetaDataEntity {
        return MetaDataEntity(id = it.id,
                audiences = emptyList(),
                eventTypes = emptyList(),
                languages = emptyList(),
                defaultLanguage = LanguageEntity("", "", 0, emptyMap(), ""),
                tracks = emptyList(),
                locations = it.locations.map { location -> toLocation(location) },
                defaultIcon = "")
    }

    private fun toMetadataModel(it: MetaDataEntity): MetaDataModel =
            MetaDataModel(
                    id = it.id,
                    locations = it.locations.map { location -> toLocationModel(location) })

    private fun toLocationModel(location: LocationsEntity): LocationsModel =
            LocationsModel(id = location.id,
                    order = location.order,
                    names = location.names,
                    icon = location.icon,
                    capacity = location.capacity)

    private fun toLocation(location: LocationsModel): LocationsEntity =
            LocationsEntity(
                    id = location.id,
                    order = location.order,
                    names = location.names,
                    icon = location.icon,
                    capacity = location.capacity
            )

    private fun toFavorite(it: FavoriteModel): FavoriteEntity {
        return FavoriteEntity(
                id = it.id,
                version = it.version
        )
    }

    private fun toSpeaker(it: SpeakerModel): SpeakerEntity {
        return SpeakerEntity(id = it.id,
                name = it.name,
                title = it.title,
                twitter = it.twitter,
                bio = it.bio,
                website = it.website,
                avatar = it.avatar)
    }

    private fun toEvent(it: EventModel): EventEntity {
        return EventEntity(
                id = it.id,
                title = it.title,
                abstractText = it.abstractText,
                startTime = it.startsAt,
                endTime = it.endsAt,
                speakerIds = it.speakerIds,
                locationId = it.locationId,
                languageId = it.languageId,
                trackId = it.trackId,
                audienceId = it.audienceId,
                eventTypeId = it.eventTypeId,
                demo = it.demo,
                simultan = it.simultan,
                veryPopular = it.veryPopular,
                fullyBooked = it.fullyBooked,
                numberOfFavorites = it.numberOfFavorites)
    }

    private fun toRooms(room: RoomModel): RoomEntity {
        return RoomEntity(
                id = room.id,
                name = room.name)
    }

    override fun saveEvents(events: List<EventEntity>) {
        cachedEvents = events
        val mappedEvents = events.map { event -> toSessionModel(event) }
        json.conference = json.conference.copy(sessions = mappedEvents)
    }

    private fun toSessionModel(event: EventEntity): EventModel = EventModel(id = event.id,
            title = event.title,
            abstractText = event.abstractText,
            startTime = toIsoTime(event.startTime),
            endTime = toIsoTime(event.endTime),
            speakerIds = event.speakerIds,
            locationId = event.locationId,
            languageId = event.languageId,
            trackId = event.trackId,
            audienceId = event.audienceId,
            eventTypeId = event.eventTypeId,
            demo = event.demo,
            simultan = event.simultan,
            veryPopular = event.veryPopular,
            fullyBooked = event.fullyBooked,
            numberOfFavorites = event.numberOfFavorites)

    //"yyyy-MM-dd'T'HH:mm:ss"
    private fun toIsoTime(startTime: GMTDate): String {
        return "${startTime.year}-${twoDigits(startTime.month.ordinal + 1 )}-${twoDigits(startTime.dayOfMonth)}T${twoDigits(startTime.hours)}:${twoDigits(startTime.minutes)}:${twoDigits(startTime.seconds)}"
    }

    private fun twoDigits(value: Int): String {
       if (value >= 10) {
           return value.toString()
       } else {
           return "0$value"
       }
    }

    override fun getEvents(): List<EventEntity> {
        return cachedEvents
    }

    override fun getSpeakers(): List<SpeakerEntity> {
        return cacheSpeakers
    }

    override fun getSpeaker(id: String): SpeakerEntity {
        return cacheSpeakers.first { it.id == id }
    }

    override fun saveSpeakers(speakers: List<SpeakerEntity>) {
        cacheSpeakers = speakers
        json.conference = json.conference.copy(speakers = speakers.map { speaker -> toSpeakerModel(speaker) })
    }

    private fun toSpeakerModel(speaker: SpeakerEntity): SpeakerModel = SpeakerModel(
            id = speaker.id,
            name = speaker.name,
            title = speaker.title,
            twitter = speaker.twitter,
            bio = speaker.bio,
            website = speaker.website,
            avatar = speaker.avatar
    )

    override fun getRooms(): List<RoomEntity> {
        return cachedRooms
    }

    override fun saveRooms(rooms: List<RoomEntity>) {
        cachedRooms = rooms
        json.conference = json.conference.copy(rooms = rooms.map { room -> toRoomsModel(room) })
    }

    override fun getEvent(id: String): EventEntity {
        return cachedEvents.first { it.id == id }
    }

    override fun getFavorites(): List<FavoriteEntity> {
        return cacheFavorites
    }

    override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
        return emptyList()
    }

    override fun getKeycloak(): KeycloakEntity {
        return KeycloakEntity("", "", "", "", "", true)
    }

    override fun getMetaData(): MetaDataEntity {
        return cachedMetaData
    }

    override fun saveMetaData(metaDataEntity: MetaDataEntity) {
        cachedMetaData = metaDataEntity
        json.conference = json.conference.copy(metaData = toMetadataModel(metaDataEntity))
    }

    override fun clearEvents() {
        cachedRooms = emptyList()
        cachedEvents = emptyList()
        cacheSpeakers = emptyList()
        cacheFavorites = emptyList()
        cachedMetaData = emptyMetaDataEntity()
        json.conference = ConferenceModel()
    }

    private fun toRoomsModel(room: RoomEntity): RoomModel = RoomModel(
            id = room.id,
            name = room.name)
}


