package org.dukecon.android.ui.features.speaker

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SpeakerListModule::class))
interface SpeakerListComponent {
    fun inject(speakerListView: SpeakerListView)
}