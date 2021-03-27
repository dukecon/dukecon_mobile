package org.dukecon.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.ktor.util.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dukecon.api.EventsList
import org.dukecon.core.IoCProvider
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.presentation.EventsViewModel


@InternalAPI
class MainActivity : AppCompatActivity() {

    private val conferenceRepository by lazy {
        IoCProvider.get<ConferenceRepository>();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            conferenceRepository.update()
        }

        setContent {
            EventsList(statusScreenViewModel)
        }
    }

    class BaseViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return creator() as T
        }
    }


    val statusScreenViewModel: EventsViewModel by lazy {
        ViewModelProvider(
            this,
            BaseViewModelFactory { EventsViewModel(conferenceRepository) }
        ).get(EventsViewModel::class.java)
    }
}