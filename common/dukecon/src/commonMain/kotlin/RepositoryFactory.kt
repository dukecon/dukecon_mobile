import io.ktor.util.date.GMTDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
import org.dukecon.domain.model.Event
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

private object RepositoryFactory {
    val repository: LocalAndRemoteDataRepository

    init {
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
        repository = LocalAndRemoteDataRepository(
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

class EventsModel(private val viewUpdate: (List<Event>) -> Unit) : BaseModel() {
    companion object {
        internal val DB_TIMESTAMP_KEY = "DbTimestampKey"
    }

    init {
        //ensureNeverFrozen()
        mainScope.launch {
            viewUpdate(RepositoryFactory.repository.getEvents(17))
        }
    }

    fun getEventsFromNetwork() {
        ktorScope.launch {
            RepositoryFactory.repository.update()
            RepositoryFactory.repository.getEvents(17)
            viewUpdate(RepositoryFactory.repository.getEvents(17))
        }
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