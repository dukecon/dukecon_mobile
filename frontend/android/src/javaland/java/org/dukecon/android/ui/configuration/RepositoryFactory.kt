package org.dukecon.android.ui.configuration

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.util.InternalAPI
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
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

object RepositoryFactory {
    fun createConferenceRepository(
        conferenceConfiguration: ConferenceConfiguration,
        okHttpClient: OkHttpClient,
        currentTimeProvider: CurrentDataTimeProvider,
        storage: ApplicationStorage
    ): ConferenceRepository {

        val api = DukeconApi(
            endpoint = conferenceConfiguration.baseUrl,
            conference = conferenceConfiguration.conferenceId,
            engine = okhttpEngine(okHttpClient)
        )

        val conferenceRemote = DukeconConferenceRemote(
            dukeconApi = api,
            conferenceConfiguration = conferenceConfiguration,
            twitterLinkMapper = TwitterUrlMapper()
        )

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
            metadataMapper = MetaDateMapper()
        )
    }

    @OptIn(InternalAPI::class)
    private fun okhttpEngine(okHttpClientInstance: OkHttpClient): HttpClientEngine {
        return OkHttpEngine(getConfig(okHttpClientInstance))

    }

    private fun getConfig(okHttpClientInstance: OkHttpClient): OkHttpConfig {
        return OkHttpConfig().apply {
            config {
                followRedirects(true)
            }
            preconfigured = okHttpClientInstance
        }
    }
}