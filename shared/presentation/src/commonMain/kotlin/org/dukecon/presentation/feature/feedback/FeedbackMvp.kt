package org.dukecon.presentation.feature.feedback

import org.dukecon.presentation.BasePresenter
import org.dukecon.presentation.BaseView


interface FeedbackMvp {

    interface View : BaseView {
        fun dismiss()
    }

    interface Presenter : BasePresenter<FeedbackMvp.View> {
        fun setSessionId(sessionId: String)

        fun submit(overall: Int, comment: String)
    }
}