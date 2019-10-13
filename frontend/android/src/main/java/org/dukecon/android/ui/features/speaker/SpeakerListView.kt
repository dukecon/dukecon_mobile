package org.dukecon.android.ui.features.speaker

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.dukecon.android.ui.ext.getComponent
import org.dukecon.android.ui.features.main.MainComponent
import org.dukecon.android.ui.features.speakerdetail.SpeakerNavigator
import org.dukecon.presentation.feature.speakers.SpeakerListContract
import org.dukecon.presentation.model.SpeakerView
import javax.inject.Inject

class SpeakerListView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    RecyclerView(context, attrs, defStyle),
    SpeakerListContract.View {
    override fun showError(throwable: Throwable) {

    }

    private val adapter: SpeakerAdapter

    @Inject
    lateinit var presenter: SpeakerListContract.Presenter
    @Inject
    lateinit var speakerNavigator: SpeakerNavigator

    init {
        context.getComponent<MainComponent>().speakerListComponent().inject(this)

        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        addItemDecoration(DividerItemDecoration(context))
        adapter = SpeakerAdapter(false) { speaker ->
            speakerNavigator.navigateToSpeaker(speaker.id)
        }
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

    override fun showNoSpeakers() {
    }
}