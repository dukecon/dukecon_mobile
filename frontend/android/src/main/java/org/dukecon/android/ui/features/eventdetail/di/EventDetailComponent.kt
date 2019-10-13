package org.dukecon.android.ui.features.eventdetail.di

import dagger.Subcomponent
import org.dukecon.android.ui.features.eventdetail.EventDetailView
import org.dukecon.android.ui.features.feedback.FeedbackComponent

@Subcomponent(modules = arrayOf(EventDetailModule::class))
interface EventDetailComponent {
    fun inject(eventDetailView: EventDetailView)
    fun feedbackComponent(): FeedbackComponent
}
