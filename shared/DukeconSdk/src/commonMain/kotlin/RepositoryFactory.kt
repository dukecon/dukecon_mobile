import io.ktor.util.date.GMTDate
import kotlinx.coroutines.launch
import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.aspects.twitter.TwitterLinks
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.cache.storage.ApplicationContext
import org.dukecon.cache.storage.ApplicationStorage
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LibrariesListRepository
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.date.now
import org.dukecon.domain.data
import org.dukecon.domain.features.search.SearchUseCase
import org.dukecon.domain.model.*
import org.dukecon.domain.repository.LibrariesRepository
import org.dukecon.platform.dispatcher
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.store.DukeconConferenceRemote
import org.dukecon.time.CurrentDataTimeProvider

val cfg =
    object : ConferenceConfiguration {
      override val baseUrl: String
        get() =
            "https://programm.javaland.eu/${getYear()}/rest/" // https://latest.dukecon.org/javaland/2018/rest/") //endpoitUrlProvider.getUrl())

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

private class RepositoryFactory(val conferenceConfiguration: ConferenceConfiguration) {
  val repository: LocalAndRemoteDataRepository
  val licensesRepository: LibrariesRepository
  val api: DukeconApi
  val search: SearchUseCase

  init {
    log(LogLevel.DEBUG, "RepositoryFactory", "start ${conferenceConfiguration.baseUrl}")

    licensesRepository = LibrariesListRepository()

    api =
        DukeconApi(
            endpoint = conferenceConfiguration.baseUrl,
            conference = conferenceConfiguration.conferenceId)

    val conferenceRemote =
        DukeconConferenceRemote(
            dukeconApi = api,
            conferenceConfiguration = conferenceConfiguration,
            twitterLinkMapper = TwitterUrlMapper())

    val cache =
        JsonSerializedConferenceDataCache(
            object : CurrentDataTimeProvider {
              override fun currentTimeMillis(): Long {
                return now()
              }
            },
            applicationStorage ?: ApplicationStorage(appContext))
    repository =
        LocalAndRemoteDataRepository(
            remoteDataStore = EventRemoteDataStore(conferenceRemote),
            localDataStore = EventCacheDataStore(cache),
            conferenceDataCache = cache,
            eventMapper = EventMapper(),
            speakerMapper = SpeakerMapper(TwitterLinks()),
            roomMapper = RoomMapper(),
            feedbackMapper = FeedbackMapper(),
            favoriteMapper = FavoriteMapper(),
            metadataMapper = MetaDateMapper())
    search = SearchUseCase(repository, dispatcher())
  }
}

fun initApplication(context: Any?) {
  appContext = context?.let { it as ApplicationContext }
  applicationStorage = ApplicationStorage(appContext)
}

fun getLocale(): String = org.dukecon.i18n.getLocalCode()

class EventsModel(private val viewUpdate: (List<Event>) -> Unit) : BaseModel() {
  init {
    // ensureNeverFrozen()
  }

  fun getEventsFromNetwork() {
    log(LogLevel.INFO, "EventsModel", "getEventsFromNetwork==>")
    ktorScope.launch {
      repositoryFactory.repository.update()
      viewUpdate(repositoryFactory.repository.getEvents(17))
      log(LogLevel.INFO, "EventsModel", "getEventsFromNetwork<==")
    }
  }

  fun getConferenceDays(viewUpdate: (List<GMTDate>) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "getConferenceDays==>")
    ktorScope.launch {
      viewUpdate(repositoryFactory.repository.getEventDates())
      log(LogLevel.INFO, "EventsModel", "getConferenceDays<==")
    }
  }

  fun getSpeakers(viewUpdate: (List<Speaker>) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "getSpeakers==>")
    ktorScope.launch {
      viewUpdate(repositoryFactory.repository.getSpeakers())
      log(LogLevel.INFO, "EventsModel", "getSpeakers<==")
    }
  }

  fun getEvents(day: Int, viewUpdate: (List<Event>) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "getEvents==>")
    ktorScope.launch {
      viewUpdate(repositoryFactory.repository.getEvents(day))
      log(LogLevel.INFO, "EventsModel", "getEvents<==")
    }
  }

  fun getEvent(eventId: String, viewUpdate: (Event) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "getEvent==>")
    ktorScope.launch {
      viewUpdate(repositoryFactory.repository.getEvent(eventId))
      log(LogLevel.INFO, "EventsModel", "getEvent<==")
    }
  }

  fun getLicenses(viewUpdate: (List<Library>) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "getLicenses==>")
    ktorScope.launch {
      viewUpdate(repositoryFactory.licensesRepository.getLibraries())
      log(LogLevel.INFO, "EventsModel", "getLicenses<==")
    }
  }

  fun getFavorites(day: Int, viewUpdate: (List<Event>) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "getFavorites==>")
    ktorScope.launch {
      val favorites = repositoryFactory.repository.getEvents(day).filter { (it.favorite.selected) }
      viewUpdate(favorites)
      log(LogLevel.INFO, "EventsModel", "getFavorites<==")
    }
  }

  fun saveFavorite(favorite: Favorite, viewUpdate: (List<Favorite>) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "getFavorites==>")
    ktorScope.launch {
      val favorites = repositoryFactory.repository.saveFavorite(favorite)
      viewUpdate(favorites)
      log(LogLevel.INFO, "EventsModel", "getFavorites<==")
    }
  }

  fun searchEventOrSpeaker(query: String, viewUpdate: (List<SearchResult>) -> Unit) {
    log(LogLevel.INFO, "EventsModel", "searchEventOrSpeaker==>")
    ktorScope.launch {
      val result = repositoryFactory.search.invoke(query)
      viewUpdate(result.data ?: emptyList())
      log(LogLevel.INFO, "EventsModel", "searchEventOrSpeaker<==")
    }
  }
}
