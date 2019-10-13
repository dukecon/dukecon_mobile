package org.dukecon.android.ui.features.event

import dagger.Module
import dagger.Provides
import org.dukecon.android.ui.injection.injectors.EventDatePresenterInj
import org.dukecon.android.ui.injection.injectors.EventListPresenterInj
import org.dukecon.presentation.feature.event.EventDateListContract
import org.dukecon.presentation.feature.event.EventListContract

@Module
class EventListModule {

    @Provides
    fun provideSessionDatePresenter(impl: EventDatePresenterInj): EventDateListContract.Presenter {
        return impl
    }

    @Provides
    fun sessionListPresenter(impl: EventListPresenterInj): EventListContract.Presenter {
        return impl
    }
}