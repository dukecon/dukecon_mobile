package org.dukecon.android

import android.app.Application
import org.dukecon.android.ioc.IoCRegisterRemoteClient
import org.dukecon.android.ui.configuration.ConfigurationFactory
import org.dukecon.android.ui.features.timemachine.CurrentTimeProviderFactory
import org.dukecon.core.IoCProvider
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.domain.features.time.CurrentTimeProvider
import org.dukecon.domain.repository.ConferenceRepository

object IoCRegister {
    fun registerEventDetail(app: Application) {
        registerTimeProvider()
        //IoCProvider.registerType(TokensStorage::class, SettingsTokenStorage())
        IoCProvider.registerType(
            ConferenceConfiguration::class,
                ConfigurationFactory.createConfiguration(app))
        IoCRegisterRemoteClient.registerConferenceApi(app)
        val conferenceRepository = IoCProvider.get<ConferenceRepository>()

    }

    fun registerTimeProvider() {
       IoCProvider.registerType(CurrentTimeProvider::class, CurrentTimeProviderFactory.newInstance())
    }
}
