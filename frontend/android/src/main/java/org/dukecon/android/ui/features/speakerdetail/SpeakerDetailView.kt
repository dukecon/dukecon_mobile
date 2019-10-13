package org.dukecon.android.ui.features.speakerdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import kotlinx.android.synthetic.main.view_speaker_detail.view.*
import org.dukecon.android.ui.R
import org.dukecon.android.ui.ext.getActivity
import org.dukecon.android.ui.ext.getComponent
import org.dukecon.android.ui.utils.DrawableUtils
import org.dukecon.presentation.feature.speakerdetail.SpeakerDetailContract
import javax.inject.Inject

class SpeakerDetailView(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        ConstraintLayout(context, attrs, defStyle), SpeakerDetailContract.View {
    override fun showError(throwable: Throwable) {

    }

    @Inject
    lateinit var presenter: SpeakerDetailContract.Presenter

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        context.getComponent<SpeakerDetailComponent>().inject(this)

        LayoutInflater.from(context).inflate(R.layout.view_speaker_detail, this, true)

        toolbar.setNavigationOnClickListener {
            if (context is Activity) {
                context.finish()
            }
        }

        var speakerId = ""
        getActivity()?.let {
            speakerId = it.intent.getStringExtra("speaker_id")
        }

        presenter.onAttach(this)
        presenter.setSpeakerId(speakerId)
    }

    override fun onDetachedFromWindow() {
        presenter.onDetach()
        super.onDetachedFromWindow()
    }

    fun setSpeakerId(speakerId: String) {
        presenter.setSpeakerId(speakerId)
    }

    override fun showSpeaker(speaker: org.dukecon.presentation.model.SpeakerDetailView) {
        name.text = speaker.name
        bio.text = speaker.bio

        if (speaker.twitterHandle.isNotEmpty()) {
            val twitterIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(speaker.twitter)
            }

            if (twitterIntent.resolveActivity(context.getPackageManager()) != null) {
                twitter.visibility = VISIBLE
                twitter.text = speaker.twitterHandle
                twitter.setCompoundDrawablesWithIntrinsicBounds(
                        DrawableUtils.create(context, R.drawable.ic_logo_twitter),
                        null,
                        null,
                        null)

                twitter.setOnClickListener {
                    context.startActivity(twitterIntent)
                }
            }
        } else {
            twitter.visibility = GONE
        }

        if (speaker.website.isNotEmpty()) {
            github.visibility = VISIBLE
            github.text = speaker.website
            github.setCompoundDrawablesWithIntrinsicBounds(
                    DrawableUtils.create(context, R.drawable.ic_logo_github),
                    null,
                    null,
                    null)

            github.setOnClickListener {
                val githubIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("${speaker.website}")
                }
                context.startActivity(githubIntent)
            }
        } else {
            github.visibility = GONE
        }

        image.apply {
            load(speaker.avatar) {
                placeholder(R.drawable.ph_speaker)
            }
        }
    }
}