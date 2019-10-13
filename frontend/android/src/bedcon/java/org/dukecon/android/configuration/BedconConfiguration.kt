package org.dukecon.android.configuration

import org.dukecon.data.source.ConferenceConfiguration

object BedconConfiguration : ConferenceConfiguration {

    override val baseUrl: String
        get() = "https://www.apachecon.com/acna19/s/rest"
    override val conferenceId: String
        get() = "zs3eoop8"
    override val speakerAvatarUrl: String
        get() = ""
    override val supportsFeedback: Boolean
        get() = false
}
