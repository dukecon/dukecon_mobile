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
            val results = ArrayList<SearchResult>()
            val dates = repository.getEventDates()
            dates.forEach { date ->
                repository.getEvents(date.dayOfMonth)
                        .filter { session ->
                            session.title.toLowerCase().contains(parameters)
                                    || session.eventDescription.toLowerCase().contains(parameters)
                        }
                        .sortedBy { it.startTime }
                        .map { results.add(SearchResult(null, it)) }
            }
            val speakers = repository.getSpeakers()
            speakers
                    .filter { speaker ->
                        speaker.title.toLowerCase().contains(parameters)
                                || speaker.bio.toLowerCase().contains(parameters)
                    }
                    .sortedBy { it.name }
                    .map { results.add(SearchResult(it, null)) }

            return results
        } catch (e: Exception) {
            log(LogLevel.ERROR, "RefreshConferenceDataUseCase", "error", e)
            throw e
        }
    }
}
