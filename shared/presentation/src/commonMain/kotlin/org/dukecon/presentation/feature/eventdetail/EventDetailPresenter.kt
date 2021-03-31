package org.dukecon.presentation.feature.eventdetail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.dukecon.domain.aspects.auth.AuthManager
import org.dukecon.domain.features.oauth.TokensStorage
import org.dukecon.domain.model.Event
import org.dukecon.domain.model.Favorite
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.presentation.CoroutinePresenter
import org.dukecon.presentation.mapper.EventMapper
import org.dukecon.presentation.mapper.SpeakerMapper

class EventDetailPresenter
constructor(
    private val conferenceRepository: ConferenceRepository,
    private val tokensStorage: TokensStorage,
    private val speakerMapper: SpeakerMapper,
    private val eventsMapper: EventMapper,
    private val authManager: AuthManager
) : CoroutinePresenter<EventDetailContract.View>(), EventDetailContract.Presenter {

  override fun showError(error: Throwable) {}

  private lateinit var view: EventDetailContract.View

  private val onRefreshListener: () -> Unit = this::refreshDataFromRepo

  override fun onAttach(view: EventDetailContract.View) {
    this.view = view
    val token = tokensStorage.getToken()
    view.setHasSession(authManager.hasSession(token))
    conferenceRepository.onRefreshListeners += onRefreshListener
  }

  override fun onDetach() {
    conferenceRepository.onRefreshListeners -= onRefreshListener
  }

  private var currentFavouriteStatus: Boolean = false

  override fun toggleFavorite() {
    launch {
      val favorites =
          withContext(Dispatchers.Default) {
            conferenceRepository.saveFavorite(Favorite(sessionId, 0, !currentFavouriteStatus))
          }
      handleSetFavoriteSuccess(favorites)
    }
  }

  private lateinit var sessionId: String

  override fun setSessionId(sessionId: String) {
    this.sessionId = sessionId
    launch {
      conferenceRepository.getEvent(sessionId)?.let { event -> handleGetEventSuccess(event) }
    }
  }

  private fun handleGetEventSuccess(event: Event) {
    this.view.let {
      it.showSessionDetail(eventsMapper.mapToView(event))
      it.showSpeakerInfo(event.speakers.map { speaker -> speakerMapper.mapToView(speaker) })
      this.currentFavouriteStatus = event.favorite.selected
      it.setIsFavorite(event.favorite.selected)
    }
  }

  private fun handleSetFavoriteSuccess(t: List<Favorite>) {
    val found = t.find { it.id == sessionId }
    if (found != null) {
      this.currentFavouriteStatus = found.selected
      view.setIsFavorite(found.selected)
    } else {
      this.currentFavouriteStatus = false
      view.setIsFavorite(false)
    }
  }

  private fun refreshDataFromRepo() {
    launch {
      conferenceRepository.getEvent(sessionId)?.let { event -> handleGetEventSuccess(event) }
    }
  }
}
