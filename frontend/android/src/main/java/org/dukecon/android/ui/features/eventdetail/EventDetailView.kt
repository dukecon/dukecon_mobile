package org.dukecon.android.ui.features.eventdetail

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_session_detail.view.*
import org.dukecon.android.ui.R
import org.dukecon.android.ui.features.feedback.FeedbackDialog
import org.dukecon.android.ui.features.speaker.SpeakerAdapter
import org.dukecon.android.ui.features.speakerdetail.SpeakerNavigator
import org.dukecon.android.ui.utils.DrawableUtils
import org.dukecon.core.IoCProvider
import org.dukecon.date.toMillis
import org.dukecon.presentation.feature.eventdetail.EventDetailContract
import org.dukecon.presentation.model.EventView
import org.dukecon.presentation.model.SpeakerView

class EventDetailView(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    CoordinatorLayout(context, attrs, defStyle), EventDetailContract.View {
  override fun showError(throwable: Throwable) {}

  private val presenter: EventDetailContract.Presenter by lazy {
    IoCProvider.get<EventDetailContract.Presenter>()
  }

  private val speakerNavigator: SpeakerNavigator by lazy { IoCProvider.get<SpeakerNavigator>() }

  private val speakerAdapter: SpeakerAdapter
  private var sessionId: String? = null

  constructor(context: Context) : this(context, null, 0)
  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

  init {
    LayoutInflater.from(context).inflate(R.layout.view_session_detail, this, true)

    speakerAdapter =
        SpeakerAdapter(true) { speaker -> speakerNavigator.navigateToSpeaker(speaker.id) }
    speakers.adapter = speakerAdapter
    speakers.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

    // initially hide the feedback button until we get a session
    feedback.hide()
    feedback.setOnClickListener { FeedbackDialog(context, sessionId!!).show() }
    favorite.setOnClickListener { presenter.toggleFavorite() }

    presenter.onAttach(this)
  }

  override fun onDetachedFromWindow() {
    presenter.onDetach()
    super.onDetachedFromWindow()
  }

  fun setSession(sessionId: String) {
    this.sessionId = sessionId
    presenter.setSessionId(sessionId)
  }

  private var hasSession: Boolean = false

  override fun setHasSession(hasSession: Boolean) {
    this.hasSession = hasSession
  }

  override fun showNoEvent() {}

  @SuppressLint("RestrictedApi")
  override fun showSessionDetail(sessionDetail: EventView) {
    toolbar.title = sessionDetail.title
    val activity = context as AppCompatActivity
    activity.setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener { activity.finish() }

    val startTime =
        DateUtils.formatDateTime(
            context, sessionDetail.startTime.toMillis(), DateUtils.FORMAT_SHOW_TIME)
    val endTime =
        DateUtils.formatDateTime(
            context, sessionDetail.endTime.toMillis(), DateUtils.FORMAT_SHOW_TIME)

    banner.text =
        String.format(
            context.getString(R.string.session_detail_time), sessionDetail.room, startTime, endTime)
    description.text = sessionDetail.description

    // TODO time

    /*val instant = Instant.ofEpochMilli(currentTimeProvider.currentTimeMillis())
    val now = instant.atZone(ZoneId.systemDefault()).toOffsetDateTime()

     */

    // TODO MPP
    /*
    if (session.startTime.isAfter(now)) {
        status.visibility = GONE
        favorite.visibility = View.VISIBLE
        feedback.visibility = View.GONE
        favorite.show()
    } else {
        status.visibility = VISIBLE
        favorite.visibility = View.GONE
        favorite.hide()

        val statusString = if (session.endTime.isAfter(now)) {
            R.string.status_in_progress
        } else {
            R.string.status_over
        }
        status.setText(statusString)

        if (conferenceConfiguration.supportsFeedback and hasSession) {
            feedback.show()
            feedback.visibility = View.VISIBLE
        }
    }
    */
  }

  override fun showSpeakerInfo(speakers: List<SpeakerView>) {
    speakerAdapter.speakers.clear()
    speakerAdapter.speakers.addAll(speakers)
    speakerAdapter.notifyDataSetChanged()
  }

  override fun setIsFavorite(isFavorite: Boolean) {
    val drawable =
        if (isFavorite) {
          DrawableUtils.create(context, R.drawable.ic_favorite_white_24dp)
        } else {
          DrawableUtils.create(context, R.drawable.ic_favorite_border_white_24dp)
        }
    favorite.setImageDrawable(drawable)
  }
}
