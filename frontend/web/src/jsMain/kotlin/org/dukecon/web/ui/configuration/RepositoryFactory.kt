package org.dukecon.web.ui.configuration

import io.ktor.client.*
import io.ktor.client.engine.js.*
import org.dukecon.aspects.twitter.TwitterLinks
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.cache.storage.ApplicationStorage
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

object RepositoryFactory {
  fun createConferenceRepository(
      conferenceConfiguration: ConferenceConfiguration,
      currentTimeProvider: CurrentDataTimeProvider,
      storage: ApplicationStorage
  ): ConferenceRepository {

    val api =
        DukeconApi(
            endpoint = conferenceConfiguration.baseUrl,
            conference = conferenceConfiguration.conferenceId,
            engine = io.ktor.client.engine.js.JsClient().create())

    val conferenceRemote =
        DukeconConferenceRemote(dukeconApi = api, conferenceConfiguration = conferenceConfiguration)

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
