package org.dukecon.domain.features.search

import kotlinx.coroutines.CoroutineDispatcher
import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.domain.SuspendUseCase
import org.dukecon.domain.model.SearchResult
import org.dukecon.domain.repository.ConferenceRepository

open class SearchUseCase constructor(
        private val repository: ConferenceRepository,
        defaultDispatcher: CoroutineDispatcher
) : SuspendUseCase<String, List<SearchResult>>(defaultDispatcher) {

    override suspend fun execute(parameters: String): List<SearchResult> {
        try {
            log(LogLevel.DEBUG, "SearchUseCase", "search for $parameters")
            val results = ArrayList<SearchResult>()
            val dates = repository.getEventDates()
            val found_events = dates.forEach { date ->
                repository.getEvents(date.dayOfMonth)
                        .filter { session ->
                            session.title.toLowerCase().contains(parameters.toLowerCase())
                                    || session.eventDescription.toLowerCase().contains(parameters.toLowerCase())
                        }
                        .sortedBy { it.startTime }
                        .map { results.add(SearchResult(null, it)) }
            }
            log(LogLevel.DEBUG, "SearchUseCase", "found events $results")
            val speakers = repository.getSpeakers()
            speakers
                    .filter { speaker ->
                        speaker.title.toLowerCase().contains(parameters.toLowerCase())
                                || speaker.name.toLowerCase().contains(parameters.toLowerCase())
                                || speaker.bio.toLowerCase().contains(parameters.toLowerCase())
                    }
                    .sortedBy { it.name }
                    .map { results.add(SearchResult(it, null)) }
            log(LogLevel.DEBUG, "SearchUseCase", "found events and speakers $results")

            return results
        } catch (e: Exception) {
            log(LogLevel.ERROR, "RefreshConferenceDataUseCase", "error", e)
            throw e
        }
    }
}
