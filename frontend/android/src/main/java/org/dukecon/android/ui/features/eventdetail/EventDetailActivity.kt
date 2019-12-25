package org.dukecon.android.ui.features.eventdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_session_detail.*
import org.dukecon.android.ui.R
import org.dukecon.android.ui.features.speakerdetail.SpeakerDetailActivity
import org.dukecon.android.ui.features.speakerdetail.SpeakerNavigator
import org.dukecon.core.IoCProvider

class EventDetailActivity : AppCompatActivity(), SpeakerNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        IoCProvider.registerType(SpeakerNavigator::class, this)

        setContentView(R.layout.activity_session_detail)


        val sessionId = intent.getStringExtra("session_id")
        sessionId?.let {
            session_detail.setSession(it)
        }
    }

    override fun navigateToSpeaker(id: String) {
        SpeakerDetailActivity.navigate(this, id)
    }
}
