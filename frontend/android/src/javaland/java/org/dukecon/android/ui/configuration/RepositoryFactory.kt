package org.dukecon.android.ui.configuration

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.util.InternalAPI
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

object RepositoryFactory {
    fun createConferenceRepository(
            conferenceConfiguration: ConferenceConfiguration,
            okHttpClient: OkHttpClient
    ): ConferenceRepository {

        val api = DukeconApi(
                endpoint = conferenceConfiguration.baseUrl,
                conference = conferenceConfiguration.conferenceId,
                engine = okhttpEngine(okHttpClient)

        )

        val conferenceRemote = DukeconConferenceRemote(
                dukeconApi = api,
                eventEntityMapper = EventEntityMapper(),
                speakerEntityMapper = SpeakerEntityMapper(conferenceConfiguration, TwitterUrlMapper()),
                metaDataEntityMapper = MetaDataEntityMapper(),
                roomEntityMapper = RoomEntityMapper()
        )

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

    @UseExperimental(InternalAPI::class)
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