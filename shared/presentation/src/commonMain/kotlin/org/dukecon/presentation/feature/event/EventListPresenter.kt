package org.dukecon.presentation.feature.event

import io.ktor.util.date.GMTDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.dukecon.date.isAfter
import org.dukecon.date.isBefore
import org.dukecon.domain.features.search.SearchUseCase
import org.dukecon.domain.features.time.CurrentTimeProvider
import org.dukecon.domain.model.Event
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.presentation.CoroutinePresenter
import org.dukecon.presentation.date.Duration
import org.dukecon.presentation.mapper.EventMapper

open class EventListPresenter
constructor(
    private val currentTimeProvider: CurrentTimeProvider,
    private val conferenceRepository: ConferenceRepository,
    private val eventsMapper: EventMapper
) : CoroutinePresenter<EventListContract.View>(), EventListContract.Presenter {

  private val onRefreshListener: () -> Unit = this::refreshDataFromRepo

  override fun onAttach(view: EventListContract.View) {
    this.view = view
    conferenceRepository.onRefreshListeners += onRefreshListener
  }

  override fun onDetach() {
    conferenceRepository.onRefreshListeners -= onRefreshListener
  }

  override fun showError(error: Throwable) {}

  private lateinit var view: EventListContract.View
  private var date: Int = 0

  private var showFavoritesOnly: Boolean = false

  override fun setDate(conferenceDay: Int, showFavoritesOnly: Boolean) {

    this.date = conferenceDay
    this.showFavoritesOnly = showFavoritesOnly

    val use = SearchUseCase(conferenceRepository, Dispatchers.Default)
    launch { use.invoke("Michal") }
    launch {
      val events = conferenceRepository.getEvents(date)
      val filtered =
          events.filter {
            if (showFavoritesOnly) {
              (it.favorite.selected)
            } else {
              true
            }
          }
      handleGetEventsSuccess(filtered)
    }
  }

  private fun refreshDataFromRepo() {
    launch {
      val events = conferenceRepository.getEvents(date)
      val filtered =
          events.filter {
            if (showFavoritesOnly) {
              (it.favorite.selected)
            } else {
              true
            }
          }
      handleGetEventsSuccess(filtered)
    }
  }

  private fun findCurrentSessionIndex(sessions: List<Event>): Int {
    // find the current session index
    val now = GMTDate(currentTimeProvider.currentTimeMillis())
    return sessions.indexOfFirst {
      val duration = Duration.inMinutes(it.startTime, it.endTime)
      now.isAfter(it.startTime) && (now.isBefore(it.endTime)) && (duration <= 100L)
    }
  }

  private fun handleGetEventsSuccess(events: List<Event>) {
    if (events.isEmpty()) {
      this.view.showNoSessions()
    } else {
      this.view.let {
        it.showSessions(events.map { event -> eventsMapper.mapToView(event) })
        it.scrollTo(findCurrentSessionIndex(events))
      }
    }
  }
}
