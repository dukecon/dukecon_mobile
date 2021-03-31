package org.dukecon.android.ui.app

import android.app.Application
import android.content.Context
import org.dukecon.android.ui.app.ioc.IoCRegisterRemoteClient
import org.dukecon.android.ui.configuration.ConfigurationFactory
import org.dukecon.android.ui.features.info.AndroidWebNavigator
import org.dukecon.android.ui.features.login.SettingsTokenStorage
import org.dukecon.android.ui.features.timemachine.CurrentTimeProviderFactory
import org.dukecon.aspects.twitter.TwitterLinks
import org.dukecon.core.IoCProvider
import org.dukecon.data.repository.LibrariesListRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.domain.aspects.auth.AuthManager
import org.dukecon.domain.features.oauth.TokensStorage
import org.dukecon.domain.features.time.CurrentTimeProvider
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.presentation.feature.event.EventDateListContract
import org.dukecon.presentation.feature.event.EventDatePresenter
import org.dukecon.presentation.feature.event.EventListContract
import org.dukecon.presentation.feature.event.EventListPresenter
import org.dukecon.presentation.feature.eventdetail.EventDetailContract
import org.dukecon.presentation.feature.eventdetail.EventDetailPresenter
import org.dukecon.presentation.feature.info.InfoContract
import org.dukecon.presentation.feature.info.InfoPresenter
import org.dukecon.presentation.feature.speakerdetail.SpeakerDetailContract
import org.dukecon.presentation.feature.speakerdetail.SpeakerDetailPresenter
import org.dukecon.presentation.feature.speakers.SpeakerListContract
import org.dukecon.presentation.feature.speakers.SpeakerListPresenter
import org.dukecon.presentation.mapper.EventMapper
import org.dukecon.presentation.mapper.LibraryMapper
import org.dukecon.presentation.mapper.SpeakerDetailMapper
import org.dukecon.presentation.mapper.SpeakerMapper

object IoCRegister {
  fun registerEventDetail(app: Application) {
    registerTimeProvider()
    IoCProvider.registerType(TokensStorage::class, SettingsTokenStorage())
    IoCProvider.registerType(
        ConferenceConfiguration::class, ConfigurationFactory.createConfiguration(app))
    IoCRegisterRemoteClient.registerConferenceApi(app)
    // val conferenceRepository = IoCProvider.get<ConferenceRepository>()

    IoCProvider.registerType(SpeakerListContract.Presenter::class, createSpeakerPresenter())

    IoCProvider.registerType(EventDetailContract.Presenter::class, createEventDetailPresenter())
    IoCProvider.registerType(EventDateListContract.Presenter::class, createEventDateListPresenter())
    IoCProvider.registerType(InfoContract.Presenter::class, createInfoContractPresenter(app))
    IoCProvider.registerType(EventListContract.Presenter::class, createEventListPresenter())
  }

  private fun createEventListPresenter(): EventListContract.Presenter {
    val currentTimeProvider = IoCProvider.get<CurrentTimeProvider>()
    val conferenceRepository = IoCProvider.get<ConferenceRepository>()
    val speakersMapper = SpeakerMapper()
    return EventListPresenter(
        currentTimeProvider, conferenceRepository, EventMapper(speakersMapper))
  }

  private fun createInfoContractPresenter(application: Context): InfoContract.Presenter {
    return InfoPresenter(
        repository = LibrariesListRepository(),
        libraryMapper = LibraryMapper(),
        webNavigator = AndroidWebNavigator(application))
  }

  private fun createSpeakerPresenter(): SpeakerListContract.Presenter {
    val conferenceRepository = IoCProvider.get<ConferenceRepository>()
    return SpeakerListPresenter(conferenceRepository, SpeakerMapper())
  }

  fun registerTimeProvider() {
    IoCProvider.registerType(CurrentTimeProvider::class, CurrentTimeProviderFactory.newInstance())
  }

  private fun createEventDetailPresenter(): EventDetailPresenter {
    val tokensStorage = IoCProvider.get<TokensStorage>()
    val conferenceRepository = IoCProvider.get<ConferenceRepository>()
    val speakerMapper = SpeakerMapper()
    val authManager = IoCProvider.get<AuthManager>()
    return EventDetailPresenter(
        conferenceRepository, tokensStorage, speakerMapper, EventMapper(speakerMapper), authManager)
  }

  private fun createEventDateListPresenter(): EventDateListContract.Presenter {
    val conferenceRepository = IoCProvider.get<ConferenceRepository>()
    return EventDatePresenter(conferenceRepository)
  }

  fun registerSpeakerDetail(dukeconApplication: DukeconApplication) {
    val conferenceRepository = IoCProvider.get<ConferenceRepository>()
    IoCProvider.registerType(
        SpeakerDetailContract.Presenter::class,
        SpeakerDetailPresenter(conferenceRepository, SpeakerDetailMapper(TwitterLinks())))
  }
}
