package org.dukecon.presentation.feature.speakerdetail

import org.dukecon.presentation.BasePresenter
import org.dukecon.presentation.BaseView
import org.dukecon.presentation.model.SpeakerDetailView

interface SpeakerDetailContract {

    interface View : BaseView {
        fun showSpeaker(speaker: SpeakerDetailView)
    }

    interface Presenter : BasePresenter<View> {
        fun setSpeakerId(speakerId: String)
    }
}