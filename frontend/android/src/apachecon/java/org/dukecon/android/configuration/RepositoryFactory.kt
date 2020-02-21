package org.dukecon.android.configuration

import okhttp3.OkHttpClient
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.domain.aspects.twitter.TwitterLinks
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

object RepositoryFactory {
    fun createConferenceRepository(conferenceConfiguration: ConferenceConfiguration,
                                   okHttpClient: OkHttpClient,
                                   currentTimeProvider: CurrentDataTimeProvider): ConferenceRepository {

        val api = DukeconApi(
                endpoint = conferenceConfiguration.baseUrl,
                conference = conferenceConfiguration.conferenceId
        )

        val conferenceRemote = DukeconConferenceRemote(
                dukeconApi = api,
                eventEntityMapper = EventEntityMapper(),
                speakerEntityMapper = SpeakerEntityMapper(conferenceConfiguration, TwitterUrlMapper()),
                metaDataEntityMapper = MetaDataEntityMapper(),
                roomEntityMapper = RoomEntityMapper()
        )

        val cache = JsonSerializedConferenceDataCache(currentTimeProvider)
        return LocalAndRemoteDataRepository(
                remoteDataStore = EventRemoteDataStore(conferenceRemote),
                localDataStore = EventCacheDataStore(cache),
                conferenceDataCache = cache,
                eventMapper = EventMapper(),
                speakerMapper = SpeakerMapper(TwitterLinks()),
                roomMapper = RoomMapper(),
                feedbackMapper = FeedbackMapper(),
                favoriteMapper = FavoriteMapper(),
                metadataMapper = MetaDateMapper())
    }
}