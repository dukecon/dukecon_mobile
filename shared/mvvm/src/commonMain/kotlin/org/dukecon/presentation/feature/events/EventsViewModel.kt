package org.dukecon.presentation

import kotlinx.coroutines.flow.Flow
import org.dukecon.common.presentation.CommonViewModel
import org.dukecon.domain.model.Event
import org.dukecon.domain.repository.ConferenceRepository

class EventsViewModel(repository: ConferenceRepository) : CommonViewModel() {
    val events: Flow<List<Event>> = repository.eventsStateModel
}