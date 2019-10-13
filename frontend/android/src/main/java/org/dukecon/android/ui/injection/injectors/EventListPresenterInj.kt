package org.dukecon.android.ui.injection.injectors

import org.dukecon.domain.aspects.auth.AuthManager
import org.dukecon.domain.features.oauth.TokensStorage
import org.dukecon.domain.features.time.CurrentTimeProvider
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.domain.repository.LibrariesRepository
import org.dukecon.presentation.feature.event.EventDatePresenter
import org.dukecon.presentation.feature.event.EventListPresenterImpl
import org.dukecon.presentation.feature.eventdetail.EventDetailPresenter
import org.dukecon.presentation.feature.feedback.FeedbackPresenter
import org.dukecon.presentation.feature.info.InfoPresenter
import org.dukecon.presentation.feature.info.WebNavigator
import org.dukecon.presentation.feature.speakerdetail.SpeakerDetailPresenter
import org.dukecon.presentation.feature.speakers.SpeakerListPresenter
import org.dukecon.presentation.mapper.EventMapper
import org.dukecon.presentation.mapper.LibraryMapper
import org.dukecon.presentation.mapper.SpeakerDetailMapper
import org.dukecon.presentation.mapper.SpeakerMapper
import javax.inject.Inject


/*
class SessionizedKtorRepositoryInj @Inject constructor(sessionizeApi: SessionizeApi)
    : SessionizedKtorRepository(sessionizeApi)
*/

class EventDatePresenterInj @Inject constructor(repository: ConferenceRepository)
    : EventDatePresenter(repository)

class EventListPresenterInj @Inject constructor(currentTimeProvider: CurrentTimeProvider,
                                                conferenceRepository: ConferenceRepository,
                                                eventsMapper: EventMapper)
    : EventListPresenterImpl(currentTimeProvider, conferenceRepository, eventsMapper)


class SpeakerListPresenterInj @Inject constructor(
        conferenceRepository: ConferenceRepository,
        speakersMapper: SpeakerMapper)
    : SpeakerListPresenter(conferenceRepository, speakersMapper)

class EventDetailPresenterInj @Inject constructor(
        conferenceRepository: ConferenceRepository,
        tokensStorage: TokensStorage,
        speakerMapper: SpeakerMapper,
        eventsMapper: EventMapper,
        authManager: AuthManager)
    : EventDetailPresenter(
        conferenceRepository,
        tokensStorage,
        speakerMapper,
        eventsMapper,
        authManager)


class FeedbackPresenterInj @Inject constructor(conferenceRepository: ConferenceRepository)
    : FeedbackPresenter(conferenceRepository)

class SpeakerDetailPresenterInj @Inject constructor(conferenceRepository: ConferenceRepository,
                                                    speakerDetailMapper: SpeakerDetailMapper)
    : SpeakerDetailPresenter(conferenceRepository, speakerDetailMapper)

class InfoPresenterInj @Inject constructor(repository: LibrariesRepository,
                                           libraryMapper: LibraryMapper,
                                           webNavigator: WebNavigator)
    : InfoPresenter(repository, libraryMapper, webNavigator)

