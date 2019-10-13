package org.dukecon.presentation.feature.speakers

import org.dukecon.presentation.BasePresenter
import org.dukecon.presentation.BaseView
import org.dukecon.presentation.model.SpeakerView

/**
 * Defines a contract of operations between the SpeakerList Presenter and SpeakerList View
 */
interface SpeakerListContract {

    interface View : BaseView {
        fun showSpeakers(speakers: Collection<SpeakerView>)
        fun showNoSpeakers()
    }

    interface Presenter : BasePresenter<View>
}