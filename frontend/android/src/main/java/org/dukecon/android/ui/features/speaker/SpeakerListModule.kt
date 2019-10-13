package org.dukecon.android.ui.features.speaker

import dagger.Module
import dagger.Provides
import org.dukecon.android.ui.injection.injectors.SpeakerListPresenterInj
import org.dukecon.presentation.feature.speakers.SpeakerListContract
import org.dukecon.presentation.feature.speakers.SpeakerListPresenter
import org.dukecon.presentation.mapper.SpeakerMapper

@Module
class SpeakerListModule {

    @Provides
    fun speakerListPresenter(impl: SpeakerListPresenterInj): SpeakerListContract.Presenter {
        return impl
    }
}