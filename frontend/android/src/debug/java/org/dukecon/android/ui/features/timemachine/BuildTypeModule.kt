package org.dukecon.android.ui.features.timemachine

import dagger.Module
import dagger.Provides
import org.dukecon.domain.features.time.CurrentTimeProvider
import javax.inject.Singleton

@Module
open class BuildTypeModule {

    @Singleton
    @Provides
    fun currentCustomizableTimeProvider(): CustomizableCurrentTimeProvider {
        return DebugCurrentTimeProvider()
    }

    @Singleton
    @Provides
    fun currentTimeProvider(provider: CustomizableCurrentTimeProvider): CurrentTimeProvider {
        return provider
    }
}
