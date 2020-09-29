package org.dukecon.mobile.common.domain

import io.ktor.util.date.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.dukecon.domain.data
import org.dukecon.domain.features.search.SearchUseCase
import org.dukecon.domain.model.Event
import org.dukecon.domain.repository.ConferenceRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class DomainModelTest {


    @Test
    fun testExample() {
        runBlocking {
            // given
            val conferenceRepository = mockk<ConferenceRepository>()
            val searchUseCase = SearchUseCase(conferenceRepository, Dispatchers.Unconfined)
            coEvery { conferenceRepository.getEventDates() } returns generateEventDates()
            coEvery { conferenceRepository.getEvents(12) } returns generateEvents()
            coEvery { conferenceRepository.getEvents(13) } returns emptyList()
            coEvery { conferenceRepository.getSpeakers() } returns emptyList()

            // when
            val result = searchUseCase("kotlin")

            // then
            coVerify { conferenceRepository.getEvents(any()) }

            assertTrue(
                result.data!!.size == 2,
                "Check kotlin mentioned 2x"
            )
        }
    }

    private fun generateEventDates(): List<GMTDate> = listOf(
        GMTDate(
            seconds = 0,
            minutes = 0,
            hours = 16,
            dayOfMonth = 12,
            month = Month.OCTOBER,
            year = 2020
        ), GMTDate(
            seconds = 0,
            minutes = 0,
            hours = 16,
            dayOfMonth = 13,
            month = Month.OCTOBER,
            year = 2020
        )
    )

    fun generateEvents(): List<Event> = listOf(
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
