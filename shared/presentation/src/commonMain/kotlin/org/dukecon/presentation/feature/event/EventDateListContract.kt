package org.dukecon.presentation.feature.event

import io.ktor.util.date.GMTDate
import org.dukecon.presentation.BasePresenter
import org.dukecon.presentation.BaseView

interface EventDateListContract {

    interface View : BaseView {
        fun showNoSessionDates()
        fun showSessionDates(sessionDates: List<GMTDate>)
        fun scrollToCurrentDay()
    }

    interface Presenter : BasePresenter<View>

}