import kotlinx.coroutines.launch
import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.cache.storage.ApplicationContext
import org.dukecon.cache.storage.ApplicationStorage
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.date.now
import org.dukecon.domain.aspects.twitter.TwitterLinks
import org.dukecon.domain.model.Event
import org.dukecon.domain.model.MetaData
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

val cfg = object : ConferenceConfiguration {
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


private var appContext: ApplicationContext? = null
private var applicationStorage: ApplicationStorage? = null
private var repositoryFactory = RepositoryFactory(cfg)

private class RepositoryFactory(val conferenceConfiguration:ConferenceConfiguration) {
    val repository: LocalAndRemoteDataRepository
    val api: DukeconApi

    init {
        log(LogLevel.DEBUG, "RepositoryFactory", "start ${conferenceConfiguration.baseUrl}")
        api = DukeconApi(
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
        }, applicationStorage ?: ApplicationStorage(appContext))
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


fun initApplication(context: Any?) {
    appContext = context?.let { it as ApplicationContext }
    applicationStorage = ApplicationStorage(appContext)
}

class EventsModel(private val viewUpdate: (List<Event>) -> Unit) : BaseModel() {
    init {
        //ensureNeverFrozen()
    }

    fun getEventsFromNetwork() {
        log(LogLevel.INFO, "EventsModel", "getEventsFromNetwork")
        ktorScope.launch {
            //RepositoryFactory.repository.update()
            val eventsDto = repositoryFactory.api.getEvents("javaland2020")
            //emptyList<org.dukecon.remote.api.Event>()//
            log(LogLevel.INFO, "EventsModel", "1")
            val maperDto = EventEntityMapper()
            log(LogLevel.INFO, "EventsModel", "2")
            val mapped = eventsDto.map { maperDto.mapFromRemote(it) }
            log(LogLevel.INFO, "EventsModel", "3")
            val eventMapper = EventMapper()
            log(LogLevel.INFO, "EventsModel", "4")
            viewUpdate(mapped.map { eventMapper.mapFromEntity(it, emptyList(), emptyList(), MetaData()) })
        }
    }
}

