package org.dukecon.configuration


class ApacheConConfiguration : ConferenceConfiguration {
    override val supportsFeedback: Boolean
        get() = false // support for feedback for "Apex Connect 2018" Conference is not deployed yet in backend
    override val speakerAvatarUrl: String
        get() = baseUrl + "speaker/images/"
    override val baseUrl: String
        get() = "https://www.apachecon.com/acna19/s/rest/"
    override val conferenceId: String
        get() = ""
}
