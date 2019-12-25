package org.dukecon.data.repository

import io.ktor.util.date.GMTDate
import org.dukecon.data.mapper.*
import org.dukecon.data.model.EventEntity
import org.dukecon.data.model.FavoriteEntity
import org.dukecon.data.model.RoomEntity
import org.dukecon.data.model.SpeakerEntity
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.domain.model.*
import org.dukecon.domain.repository.ConferenceRepository

/**
 * Provides an implementation of the [ConferenceRepository] interface for communicating to and from
 * data sources
 */
class LocalAndRemoteDataRepository constructor(
        private val remoteDataStore: EventRemoteDataStore,
        private val localDataStore: EventCacheDataStore,
        private val eventMapper: EventMapper,
        private val speakerMapper: SpeakerMapper,
        private val roomMapper: RoomMapper,
        private val feedbackMapper: FeedbackMapper,
        private val favoriteMapper: FavoriteMapper,
        private val metadataMapper: MetaDateMapper

) : ConferenceRepository {

    override var onRefreshListeners: List<() -> Unit> = emptyList()

    override suspend fun getKeyCloak(): Keycloak = Keycloak(
            realm = "",
            authServerUrl = "",
            sslRequired = "",
            resource = "",
            redirectUri = "",
            useAccountManagement = false
    )

    override suspend fun submitFeedback(feedback: Feedback): Any {
        return try {
            remoteDataStore.submitFeedback(feedbackMapper.mapToEntity(feedback))
        } catch (e: Exception) {
            Any()
        }
    }

    override suspend fun saveFavorite(favorite: Favorite): List<Favorite> {

        // merge local list
        val localFavorites = localDataStore.getFavorites().toMutableList()

        localFavorites.removeAll { it.id == favorite.id }
        if (favorite.selected) {
            localFavorites.add(FavoriteEntity(favorite.id, favorite.version))
        }

        // save to remote
        try {
            val remoteFavorites = remoteDataStore.getFavorites().toMutableList()
            remoteFavorites.removeAll { it.id == favorite.id }

            val favorites = mergeFavorites(localFavorites, remoteFavorites)
            localDataStore.saveFavorites(favorites)
            remoteDataStore.saveFavorites(favorites)
        } catch (e: Exception) {
            val favorites = mergeFavorites(localFavorites, emptyList())
            localDataStore.saveFavorites(favorites)
        }

        callRefreshListeners()

        return getFavorites()
    }

    override suspend fun getFavorites(): List<Favorite> {
        return localDataStore.getFavorites().map { list ->
            favoriteMapper.mapFromEntity(list)
        }
    }

    override suspend fun getSpeaker(id: String): Speaker {
        return speakerMapper.mapFromEntity(localDataStore.getSpeakers().first { it.id == id })
    }

    override suspend fun getEvent(id: String): Event {
        val dataStore = localDataStore

        val speakers = dataStore.getSpeakers().map { speakerMapper.mapFromEntity(it) }
        val favorites = dataStore.getFavorites().map { favoriteMapper.mapFromEntity(it) }
        val metaData = metadataMapper.mapFromEntity(dataStore.getMetaData())

        return eventMapper.mapFromEntity(dataStore.getEvents().first { it.id == id }, speakers, favorites, metaData)
    }

    override suspend fun getRooms(): List<Room> {
        val dataStore = localDataStore
        return dataStore.getRooms().map { roomMapper.mapFromEntity(it) }
    }

    override suspend fun saveSpeakers(speakers: List<Speaker>) {
        val eventEntities = speakers.map { speakerMapper.mapToEntity(it) }
        return saveSpeakersEntities(eventEntities)
    }

    override suspend fun saveRooms(rooms: List<Room>) {
        val eventEntities = rooms.map { roomMapper.mapToEntity(it) }
        return savRoomsEntities(eventEntities)
    }

    override suspend fun getSpeakers(): List<Speaker> {
        val dataStore = localDataStore
        return dataStore.getSpeakers()
                .map {
                    speakerMapper.mapFromEntity(it)
                }.sortedBy { it.name }
    }

    override suspend fun getEventDates(): List<GMTDate> {
        val dataStore = localDataStore
        val events = dataStore.getEvents()
        return events.distinctBy { it.startTime.dayOfMonth }
                .map {
                    it.startTime
                }.sortedBy { it.dayOfMonth }
    }

    override suspend fun saveEvents(events: List<Event>) {
        val eventEntities = events.map { eventMapper.mapToEntity(it) }
        return saveEventEntities(eventEntities)
    }

    private fun saveEventEntities(events: List<EventEntity>) {
        return localDataStore.saveEvents(events)
    }

    private fun saveSpeakersEntities(speakers: List<SpeakerEntity>) {
        return localDataStore.saveSpeakers(speakers)
    }

    private fun savRoomsEntities(speakers: List<RoomEntity>) {
        return localDataStore.saveRooms(speakers)
    }

    private fun callRefreshListeners() {
        onRefreshListeners.forEach { it() }
    }


    override suspend fun update() {
        try {
            val events = remoteDataStore.getEvents()
            localDataStore.saveEvents(events)
            localDataStore.saveMetaData(remoteDataStore.getMetaData())
            //val merged = mergeFavorites(localDataStore.getFavorites(), remoteDataStore.getFavorites())
            // localDataStore.saveFavorites(merged)
            localDataStore.saveSpeakers(remoteDataStore.getSpeakers())

            callRefreshListeners()
        } catch (e: Exception) {
            localDataStore.saveEvents(emptyList())
        }
    }

    private fun mergeFavorites(favorites1: List<FavoriteEntity>, favorites2: List<FavoriteEntity>): List<FavoriteEntity> {
        val result = ArrayList<FavoriteEntity>()
        for (e in favorites1) {
            result.add(e)
        }
        for (e in favorites2) {
            if (!result.contains(e)) {
                result.add(e)
            }
        }
        return result
    }

    override suspend fun getEvents(day: Int): List<Event> {
        val dataStore = localDataStore

        val speakers = dataStore.getSpeakers().map { speakerMapper.mapFromEntity(it) }
        val favorites = dataStore.getFavorites().map { favoriteMapper.mapFromEntity(it) }
        val metaData = metadataMapper.mapFromEntity(dataStore.getMetaData())

        val eventEntities = dataStore.getEvents()

        return eventEntities.allBy {
            if (day > 0) {
                it.startTime.dayOfMonth == day
            } else {
                true
            }
        }.map {
            eventMapper.mapFromEntity(it, speakers, favorites, metaData)
        }.sortedBy {
            it.startTime
        }
    }

    override suspend fun getMetaData(): MetaData {
        val dataStore = localDataStore

        return metadataMapper.mapFromEntity(dataStore.getMetaData())
    }


    /**
     * Returns a list containing elements from the given collection by the given [selector] function.
     */
    private fun <T> Iterable<T>.allBy(selector: (T) -> Boolean): List<T> {
        val list = ArrayList<T>()
        for (e in this) {
            if (selector(e)) {
                list.add(e)
            }
        }
        return list
    }
}