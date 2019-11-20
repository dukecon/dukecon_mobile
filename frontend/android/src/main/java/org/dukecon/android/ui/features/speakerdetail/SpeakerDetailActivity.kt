package org.dukecon.android.ui.features.speakerdetail

import android.app.Activity
import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_speaker_detail.*
import org.dukecon.android.ui.R

class SpeakerDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun navigate(activity: Activity, speakerId: String) {
            val intent = Intent(activity, SpeakerDetailActivity::class.java)
            intent.putExtra("speaker_id", speakerId)

            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
        }

        setContentView(R.layout.activity_speaker_detail)

        val speakerId = intent.getStringExtra("speaker_id")
        speakerId?.let {
            speaker_detail_view.setSpeakerId(speakerId)
        }
    }
}