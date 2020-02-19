import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.date.now
import org.dukecon.domain.aspects.twitter.TwitterLinks
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

object RepositoryFactory {
    fun createConferenceRepositoryInstance(): ConferenceRepository {
        log(LogLevel.DEBUG, "RepositoryFactory", "start")
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

        val cache = JsonSerializedConferenceDataCache(object : CurrentDataTimeProvider {
            override fun currentTimeMillis(): Long {
                return now()
            }
        })
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

val conferenceConfiguration = object : ConferenceConfiguration {
    override val baseUrl: String
        get() = "https://programm.javaland.eu/${getYear()}/rest/" //https://latest.dukecon.org/javaland/2018/rest/") //endpoitUrlProvider.getUrl())

    private fun getYear(): String {
        return "2020"
    }

    override val conferenceId: String
        get() = "javaland${getYear()}"

    override val speakerAvatarUrl: String
        get() = baseUrl + "speaker/images/"

    override val supportsFeedback = false
}