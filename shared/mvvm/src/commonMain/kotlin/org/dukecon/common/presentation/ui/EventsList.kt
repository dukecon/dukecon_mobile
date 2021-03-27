package org.dukecon.api

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.dukecon.presentation.EventsViewModel

@Composable
fun EventsList(eventsViewModel: EventsViewModel) {

    val eventsState = eventsViewModel.events.collectAsState(
        emptyList(),
        eventsViewModel.clientScope.coroutineContext
    )

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("DukeCon") })
            }) {
            LazyColumn {
                items(eventsState.value.size) { eventIndex ->
                    Text(eventsState.value[eventIndex].title ?: "Event")
                }
            }
        }
    }
}
