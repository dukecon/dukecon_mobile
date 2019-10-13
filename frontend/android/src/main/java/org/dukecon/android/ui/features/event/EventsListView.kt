package org.dukecon.android.ui.features.event

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.ktor.util.date.GMTDate
import org.dukecon.android.ui.ext.getComponent
import org.dukecon.android.ui.features.main.MainComponent
import org.dukecon.domain.features.time.CurrentTimeProvider
import org.dukecon.presentation.feature.event.EventListContract
import org.dukecon.presentation.model.EventView
import javax.inject.Inject

class EventsListView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        RecyclerView(context, attrs, defStyle), EventListContract.View {

    override fun showError(throwable: Throwable) {

    }

    private val adapter: EventsAdapter
    private var date: GMTDate? = null

    @Inject
    lateinit var presenter: EventListContract.Presenter
    @Inject
    lateinit var sessionNavigator: SessionNavigator
    @Inject
    lateinit var currentTimeProvider: CurrentTimeProvider


    init {
        context.getComponent<MainComponent>().sessionListComponent().inject(this)

        layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        addItemDecoration(EventItemDecoration(context))
        adapter = EventsAdapter(currentTimeProvider) { session ->
            sessionNavigator.showSession(session)
        }
        super.setAdapter(adapter)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.onAttach(this)
        date?.let { presenter.setDate(it.dayOfMonth, this.showFavoritesOnly) }
    }

    override fun onDetachedFromWindow() {
        presenter.onDetach()
        super.onDetachedFromWindow()
    }

    private var showFavoritesOnly: Boolean = false;

    fun setDate(date: GMTDate, showFavoritesOnly: Boolean) {
        this.showFavoritesOnly = showFavoritesOnly
        this.date = date
    }

    override fun showNoSessions() {
        // todo
    }

    override fun showSessions(sessions: List<EventView>) {
        adapter.sessions.clear()
        adapter.sessions.addAll(sessions)
        adapter.notifyDataSetChanged()
    }

    override fun scrollTo(index: Int) {
        scrollToPosition(index)
    }
}
