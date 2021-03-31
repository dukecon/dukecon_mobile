package org.dukecon.android.ui.features.speaker

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.dukecon.android.ui.features.speakerdetail.SpeakerNavigator
import org.dukecon.core.IoCProvider
import org.dukecon.presentation.feature.speakers.SpeakerListContract
import org.dukecon.presentation.model.SpeakerView

class SpeakerListView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    RecyclerView(context, attrs, defStyle), SpeakerListContract.View {
  override fun showError(throwable: Throwable) {}

  private val adapter: SpeakerAdapter

  private val presenter: SpeakerListContract.Presenter by lazy {
    IoCProvider.get<SpeakerListContract.Presenter>()
  }

  private val speakerNavigator: SpeakerNavigator by lazy { IoCProvider.get<SpeakerNavigator>() }

  init {
    layoutParams =
        ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    layoutManager = LinearLayoutManager(context, VERTICAL, false)
    addItemDecoration(DividerItemDecoration(context))
    adapter = SpeakerAdapter(false) { speaker -> speakerNavigator.navigateToSpeaker(speaker.id) }
    super.setAdapter(adapter)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.onAttach(this)
  }

  override fun onDetachedFromWindow() {
    presenter.onDetach()
    super.onDetachedFromWindow()
  }

  override fun showSpeakers(speakers: Collection<SpeakerView>) {
    adapter.speakers.clear()
    adapter.speakers.addAll(speakers)
    adapter.notifyDataSetChanged()
  }

  override fun showNoSpeakers() {}
}
