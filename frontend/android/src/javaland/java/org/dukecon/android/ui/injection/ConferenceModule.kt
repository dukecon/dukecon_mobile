package org.dukecon.android.ui.injection

import android.app.Application
import dagger.Module
import dagger.Provides
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.android.ui.configuration.JavalandConfiguration
import org.dukecon.android.ui.configuration.JavalandOAuthConfiguration
import org.dukecon.data.source.OAuthConfiguration

@Module
open class ConferenceModule {

    @Provides
    fun provideConfiguration(application: Application): ConferenceConfiguration {
        return JavalandConfiguration(application)
    }

    @Provides
    fun provideOAuthConfiguration(application: Application): OAuthConfiguration {
        return JavalandOAuthConfiguration(application)
    }
}
