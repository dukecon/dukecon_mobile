package org.dukecon.android.ui.features.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dukecon.android.ui.R
import org.dukecon.android.ui.ext.getAppComponent
import org.dukecon.android.ui.features.event.FavoritesFragment
import org.dukecon.android.ui.features.event.ScheduleFragment
import org.dukecon.android.ui.features.event.SessionNavigator
import org.dukecon.android.ui.features.eventdetail.EventDetailActivity
import org.dukecon.android.ui.features.info.InfoFragment
import org.dukecon.android.ui.features.speaker.SpeakersFragment
import org.dukecon.android.ui.features.speakerdetail.SpeakerDetailActivity
import org.dukecon.android.ui.features.speakerdetail.SpeakerNavigator
import org.dukecon.android.ui.utils.consume
import org.dukecon.android.ui.utils.inTransaction
import org.dukecon.domain.aspects.auth.AuthManager
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.presentation.model.EventView
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), SessionNavigator,
        SpeakerNavigator, CoroutineScope {

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    companion object {
        private const val FRAGMENT_ID = R.id.content
    }

    private lateinit var component: MainComponent

    @Inject
    lateinit var exchangeCodeForToken: AuthManager

    @Inject
    lateinit var conferenceRepository: ConferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mJob = Job()

        component = getAppComponent().mainComponent(MainModule(this, this))
        component.inject(this)

        setContentView(R.layout.activity_main)

        setTitle(R.string.event_name)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_schedule -> consume {
                    replaceFragment(ScheduleFragment())
                }
                R.id.action_favorites -> consume {
                    replaceFragment(FavoritesFragment())
                }
                R.id.action_speakers -> consume {
                    // Scroll to current event next time the schedule is opened.
                    replaceFragment(SpeakersFragment())
                }
                R.id.action_info -> consume {
                    // Scroll to current event next time the schedule is opened.
                    replaceFragment(InfoFragment())
                }
                else -> false
            }
        }

        // Add a listener to prevent reselects from being treated as selects.
        navigation.setOnNavigationItemReselectedListener {}

        if (savedInstanceState == null) {
            // Show Schedule on first creation
            updateData()
            navigation.selectedItemId = R.id.action_schedule
            replaceFragment(ScheduleFragment())
        } else {
            // Find the current fragment
            currentFragment =
                    supportFragmentManager.findFragmentById(FRAGMENT_ID)
                            ?: throw IllegalStateException("Activity recreated, but no fragment found!")
        }

        val uri = intent.data
        if (uri != null) {
            val code = uri.getQueryParameter("code") ?: ""
            launch(Dispatchers.IO) {
                try {
                    exchangeCodeForToken.exchangeToken(code)
                } catch (e: Exception) {
                }
            }
        }
    }

    private fun updateData() {
        launch(Dispatchers.IO) {
            conferenceRepository.update()
        }
    }

    private lateinit var currentFragment: Fragment

    private fun <F> replaceFragment(fragment: F) where F : Fragment {
        supportFragmentManager.inTransaction {
            currentFragment = fragment
            replace(FRAGMENT_ID, fragment)
        }
    }

    override fun getSystemService(name: String?): Any {
        return when (name) {
            "component" -> component
            else -> super.getSystemService(name ?: "")
        }
    }

    override fun showSession(session: EventView) {
        val intent = Intent(this, EventDetailActivity::class.java)
        intent.putExtra("session_id", session.id)
        startActivity(intent)
    }

    override fun navigateToSpeaker(id: String) {
        SpeakerDetailActivity.navigate(this, id)
    }
}
