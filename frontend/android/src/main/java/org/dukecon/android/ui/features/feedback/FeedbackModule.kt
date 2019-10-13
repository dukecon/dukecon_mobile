package org.dukecon.android.ui.features.feedback

import dagger.Module
import dagger.Provides
import org.dukecon.android.ui.injection.injectors.FeedbackPresenterInj
import org.dukecon.presentation.feature.feedback.FeedbackMvp

@Module
class FeedbackModule {
    @Provides
    fun presenter(impl: FeedbackPresenterInj): FeedbackMvp.Presenter {
        return impl
    }
}