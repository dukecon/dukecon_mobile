package org.dukecon.android.ui.features.login.di

import dagger.Module
import dagger.Provides
import org.dukecon.android.ui.features.login.MainNavigator

@Module
class LoginModule(val mainNavigator: MainNavigator) {

    @Provides
    fun provideSpeakerNavigator(): MainNavigator {
        return mainNavigator
    }
}