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
import org.dukecon.remote.sessionize.api.SessionizeApiImpl
import org.dukecon.remote.store.SessionizeConferenceRemotes

object RepositoryFactory {
    fun createConferenceRepository(conferenceConfiguration: ConferenceConfiguration,
                                   okHttpClient: OkHttpClient): ConferenceRepository {

        val api = SessionizeApiImpl(conferenceConfiguration.conferenceId)

        val conferenceRemote = SessionizeConferenceRemotes(api)

        return LocalAndRemoteDataRepository(
                remoteDataStore = EventRemoteDataStore(conferenceRemote),
                localDataStore = EventCacheDataStore(JsonSerializedConferenceDataCache()),
                eventMapper = EventMapper(),
                speakerMapper = SpeakerMapper(TwitterLinks()),
                roomMapper = RoomMapper(),
                feedbackMapper = FeedbackMapper(),
                favoriteMapper = FavoriteMapper(),
                metadataMapper = MetaDateMapper())
    }
}