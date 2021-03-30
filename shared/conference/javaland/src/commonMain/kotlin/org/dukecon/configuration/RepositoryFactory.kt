package org.dukecon.configuration

import io.ktor.client.engine.*
import kotlinx.datetime.Clock
import org.dukecon.aspects.twitter.TwitterLinks
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.cache.storage.ApplicationContext
import org.dukecon.cache.storage.ApplicationStorage
import org.dukecon.cache.storage.applicationStorageFactory
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

object RepositoryFactory {
    fun createConferenceRepository(
        conferenceConfiguration: ConferenceConfiguration,
        engine: HttpClientEngine? = null,
        currentTimeProvider: CurrentDataTimeProvider? = null,
        storage: ApplicationStorage? = null
    ): ConferenceRepository {
        val timeProvider = currentTimeProvider ?: object : CurrentDataTimeProvider {
            override fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()
        }

        val actualStorage = applicationStorageFactory(null)

        val api = DukeconApi(
            endpoint = conferenceConfiguration.baseUrl,
            conference = conferenceConfiguration.conferenceId,
            engine = engine
        )

        val conferenceRemote = DukeconConferenceRemote(
            dukeconApi = api,
            conferenceConfiguration = conferenceConfiguration
        )

        val cache = JsonSerializedConferenceDataCache(timeProvider, actualStorage)
        return LocalAndRemoteDataRepository(
            remoteDataStore = EventRemoteDataStore(conferenceRemote),
            localDataStore = EventCacheDataStore(cache),
            conferenceDataCache = cache,
            eventMapper = EventMapper(),
            speakerMapper = SpeakerMapper(TwitterLinks()),
            roomMapper = RoomMapper(),
            feedbackMapper = FeedbackMapper(),
            favoriteMapper = FavoriteMapper(),
            metadataMapper = MetaDateMapper()
        )
    }
}