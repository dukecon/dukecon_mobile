package org.dukecon.mobile.common.data.cache

import io.ktor.util.date.*
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.cache.storage.ApplicationStorage
import org.dukecon.data.mapper.EventMapper
import org.dukecon.domain.model.Event
import org.dukecon.time.CurrentDataTimeProvider
import org.junit.Test
import kotlin.test.assertTrue

class JsonSerializedConferenceDataCacheTest {

    private val cache: JsonSerializedConferenceDataCache = JsonSerializedConferenceDataCache(
        object : CurrentDataTimeProvider {
            override fun currentTimeMillis(): Long {
                return 0
            }

        },
        ApplicationStorage(null)
    )

    val mapper = EventMapper()

    @Test
    fun `initial cache is empty`() {
        assertTrue(cache.getEvents().isEmpty())
        assertTrue(cache.getRooms().isEmpty())
        assertTrue(cache.getFavorites().isEmpty())
        assertTrue(cache.getSpeakers().isEmpty())
    }

    @Test
    fun `saved events are returned back`() {
        // given
        cache.saveEvents(generateEvents().map { mapper.mapToEntity(it) })

        // when
        val result = cache.getEvents()

        // then
        assertTrue(result.size == 2)
    }

    private fun generateEvents(): List<Event> = listOf(
        Event(
            eventId = "1", title = "Talk 1", eventDescription = "kotlin talk 1",
            startTime = GMTDate(
                seconds = 0,
                minutes = 0,
                hours = 16,
                dayOfMonth = 13,
                month = Month.OCTOBER,
                year = 2020
            ),
            endTime = GMTDate(
                seconds = 0,
                minutes = 45,
                hours = 16,
                dayOfMonth = 13,
                month = Month.OCTOBER,
                year = 2020
            )
        ),
        Event(
            eventId = "2",
            title = "Talk 2",
            eventDescription = "kotlin talk 2",
            startTime = GMTDate(
                seconds = 0,
                minutes = 0,
                hours = 17,
                dayOfMonth = 13,
                month = Month.OCTOBER,
                year = 2020
            ),
            endTime = GMTDate(
                seconds = 0,
                minutes = 30,
                hours = 17,
                dayOfMonth = 13,
                month = Month.OCTOBER,
                year = 2020
            )
        )
    )
}