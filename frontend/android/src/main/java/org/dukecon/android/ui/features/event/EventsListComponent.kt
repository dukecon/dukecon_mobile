package org.dukecon.android.ui.features.event

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(EventListModule::class))
interface EventsListComponent {
    fun inject(eventsListView: EventsListView)
    fun inject(eventDateView: EventDateView)
}
