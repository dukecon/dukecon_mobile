import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.domain.aspects.twitter.TwitterLinks
import org.dukecon.remote.api.MacounApi
import org.dukecon.remote.mapper.EventEntityMapper
import org.dukecon.remote.mapper.MetaDataEntityMapper
import org.dukecon.remote.mapper.RoomEntityMapper
import org.dukecon.remote.mapper.SpeakerEntityMapper
import org.dukecon.remote.store.MacounConferenceRemote
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonMacounRepositoryTest {
    @Test
    fun testMacoun() = runTest {
        val api = MacounApi("https://backend.macoun.de/fahrplan", "2019.json")

        val conferenceRemote = MacounConferenceRemote(
                macounApi = api,
                eventEntityMapper = EventEntityMapper(),
                speakerEntityMapper = SpeakerEntityMapper(object : ConferenceConfiguration {
                    override val baseUrl: String
                        get() = ""
                    override val conferenceId: String
                        get() = ""
                    override val speakerAvatarUrl: String
                        get() = ""
                    override val supportsFeedback: Boolean
                        get() = false
                }, TwitterLinks()),
                metaDataEntityMapper = MetaDataEntityMapper(),
                roomEntityMapper = RoomEntityMapper()
        )
        val repo = LocalAndRemoteDataRepository(
                remoteDataStore = EventRemoteDataStore(conferenceRemote),
                localDataStore = EventCacheDataStore(JsonSerializedConferenceDataCache()),
                eventMapper = EventMapper(),
                speakerMapper = SpeakerMapper(TwitterLinks()),
                roomMapper = RoomMapper(),
                feedbackMapper = FeedbackMapper(),
                favoriteMapper = FavoriteMapper(),
                metadataMapper = MetaDateMapper())

        val events = repo.getEvents(19)
        assertTrue { events.isEmpty() }

        repo.update()
        val updatedEvents = repo.getEvents(19)
        assertTrue { updatedEvents.isNotEmpty() }
    }

    @Test
    fun testJavalandDays() = runTest {
        val api = MacounApi("https://backend.macoun.de/fahrplan", "2019.json")

        val conferenceRemote = MacounConferenceRemote(
                macounApi = api,
                eventEntityMapper = EventEntityMapper(),
                speakerEntityMapper = SpeakerEntityMapper(object : ConferenceConfiguration {
                    override val baseUrl: String
                        get() = ""
                    override val conferenceId: String
                        get() = ""
                    override val speakerAvatarUrl: String
                        get() = ""
                    override val supportsFeedback: Boolean
                        get() = false
                }, TwitterLinks()),
                metaDataEntityMapper = MetaDataEntityMapper(),
                roomEntityMapper = RoomEntityMapper()
        )
        val repo = LocalAndRemoteDataRepository(
                remoteDataStore = EventRemoteDataStore(conferenceRemote),
                localDataStore = EventCacheDataStore(JsonSerializedConferenceDataCache()),
                eventMapper = EventMapper(),
                speakerMapper = SpeakerMapper(TwitterLinks()),
                roomMapper = RoomMapper(),
                feedbackMapper = FeedbackMapper(),
                favoriteMapper = FavoriteMapper(),
                metadataMapper = MetaDateMapper())

        val events = repo.getEvents(5)
        assertTrue { events.isEmpty() }

        repo.update()
        val dates = repo.getEventDates()
        assertTrue { dates.isNotEmpty() }
    }
}