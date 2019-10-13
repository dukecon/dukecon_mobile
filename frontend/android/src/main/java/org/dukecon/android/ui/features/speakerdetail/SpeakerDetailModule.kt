package org.dukecon.android.ui.features.speakerdetail

import dagger.Module
import dagger.Provides
import org.dukecon.android.ui.injection.injectors.SpeakerDetailPresenterInj
import org.dukecon.presentation.feature.speakerdetail.SpeakerDetailContract

@Module
class SpeakerDetailModule {
    @Provides
    fun speakerDetailPresenter(impl: SpeakerDetailPresenterInj): SpeakerDetailContract.Presenter {
        return impl
    }
}