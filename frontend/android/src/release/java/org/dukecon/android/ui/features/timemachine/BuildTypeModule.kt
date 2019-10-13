package org.dukecon.android.ui.features.timemachine

import dagger.Module
import dagger.Provides
import org.dukecon.domain.features.time.CurrentTimeProvider
import javax.inject.Singleton

@Module
open class BuildTypeModule {

    @Singleton
    @Provides
    fun currentTimeProvider(): CurrentTimeProvider {
        return ReleaseCurrentTimeProvider()
    }
}
