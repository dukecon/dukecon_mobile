package org.dukecon.android.ui.features.main

import dagger.Module
import dagger.Provides
import org.dukecon.android.ui.features.event.SessionNavigator
import org.dukecon.android.ui.features.speakerdetail.SpeakerNavigator

@Module
class MainModule(val sessionNavigator: SessionNavigator, val speakerNavigator: SpeakerNavigator) {

    @Provides
    fun sessionNavigator(): SessionNavigator {
        return sessionNavigator
    }

    @Provides
    fun speakerNavigator(): SpeakerNavigator {
        return speakerNavigator
    }
}