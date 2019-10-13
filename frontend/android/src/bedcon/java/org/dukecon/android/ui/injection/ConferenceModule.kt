package org.dukecon.android.ui.injection

import dagger.Module
import dagger.Provides
import org.dukecon.android.configuration.BedconConfiguration
import org.dukecon.data.source.ConferenceConfiguration

@Module
open class ConferenceModule {
    @Provides
    fun provideConfiguration(): ConferenceConfiguration {
        return BedconConfiguration
    }
}
