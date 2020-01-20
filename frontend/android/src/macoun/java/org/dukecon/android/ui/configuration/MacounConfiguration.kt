package org.dukecon.android.ui.configuration

import org.dukecon.data.source.ConferenceConfiguration

object MacounConfiguration : ConferenceConfiguration {
    override val baseUrl: String
        get() = "https://backend.macoun.de/fahrplan"
    override val conferenceId: String
        get() = "2019.json"
    override val speakerAvatarUrl: String
        get() = ""
    override val supportsFeedback: Boolean
        get() = false
}
