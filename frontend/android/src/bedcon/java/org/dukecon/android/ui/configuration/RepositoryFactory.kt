package org.dukecon.android.ui.configuration

import okhttp3.OkHttpClient
import org.dukecon.aspects.twitter.TwitterLinks
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.cache.storage.ApplicationStorage
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.sessionize.api.SessionizeApiImpl
import org.dukecon.sessionize.store.SessionizeConferenceRemotes
import org.dukecon.time.CurrentDataTimeProvider

object RepositoryFactory {
  fun createConferenceRepository(
      conferenceConfiguration: ConferenceConfiguration,
      okHttpClient: OkHttpClient,
      currentTimeProvider: CurrentDataTimeProvider,
      storage: ApplicationStorage
  ): ConferenceRepository {

    val api = SessionizeApiImpl(conferenceConfiguration.conferenceId)

    val conferenceRemote = SessionizeConferenceRemotes(api)

    val cache = JsonSerializedConferenceDataCache(currentTimeProvider, storage)

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
