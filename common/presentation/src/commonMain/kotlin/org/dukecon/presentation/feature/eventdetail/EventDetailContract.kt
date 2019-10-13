package org.dukecon.presentation.feature.eventdetail

import org.dukecon.presentation.BasePresenter
import org.dukecon.presentation.BaseView
import org.dukecon.presentation.model.EventView
import org.dukecon.presentation.model.SpeakerView

interface EventDetailContract {

    interface View : BaseView {
        fun showSessionDetail(sessionDetail: EventView)
        fun showSpeakerInfo(speakers: List<SpeakerView>)
        fun setIsFavorite(isFavorite: Boolean)
        fun showNoEvent()
        fun setHasSession(hasSession: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun toggleFavorite()
        fun setSessionId(sessionId: String)
    }
}