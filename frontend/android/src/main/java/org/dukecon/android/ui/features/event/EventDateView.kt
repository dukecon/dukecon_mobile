package org.dukecon.android.ui.features.event

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import io.ktor.util.date.GMTDate
import kotlinx.android.synthetic.main.view_sessions.view.*
import org.dukecon.android.ui.R
import org.dukecon.core.IoCProvider
import org.dukecon.domain.features.time.CurrentTimeProvider
import org.dukecon.presentation.feature.event.EventDateListContract

class EventDateView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        FrameLayout(context, attrs, defStyle), EventDateListContract.View {

    override fun showSessionDates(sessionDates: List<GMTDate>) {
        adapter.showEventDates(sessionDates, showFavoritesOnly)
        adapter.notifyDataSetChanged()

        if (sessionDates.size > 1) {
            tabs.visibility = View.VISIBLE
        }
    }

    override fun showError(throwable: Throwable) {

    }

    private val currentTimeProvider: CurrentTimeProvider by lazy {
        IoCProvider.get<CurrentTimeProvider>()
    }

    private val presenter: EventDateListContract.Presenter by lazy {
        IoCProvider.get<EventDateListContract.Presenter>()
    }

    private val adapter: SessionPagerAdapter

    var showFavoritesOnly: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_sessions, this, true)

        adapter = SessionPagerAdapter()
        pager.adapter = adapter

        tabs.setupWithViewPager(pager)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.onAttach(this)
    }

    override fun onDetachedFromWindow() {
        presenter.onDetach()
        super.onDetachedFromWindow()
    }

    override fun showNoSessionDates() {
        adapter.clear()
        adapter.notifyDataSetChanged()
    }

    override fun scrollToCurrentDay() {
        if (adapter.dates.isNotEmpty()) {

            val now = GMTDate(currentTimeProvider.currentTimeMillis())
            val index = adapter.dates.indexOfFirst { now.dayOfMonth == it.dayOfMonth }
            if (index >= 0) {
                pager.setCurrentItem(index, false)
            }
        }
    }
}