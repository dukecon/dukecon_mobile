package org.dukecon.android.ui.features.feedback

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(FeedbackModule::class))
interface FeedbackComponent {
    fun inject(feedbackDialog: FeedbackDialog)
}