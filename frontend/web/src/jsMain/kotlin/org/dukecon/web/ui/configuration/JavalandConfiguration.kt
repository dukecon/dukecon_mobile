package org.dukecon.web.ui.configuration

import org.dukecon.data.source.ConferenceConfiguration

class JavalandConfiguration() : ConferenceConfiguration {
  override val supportsFeedback: Boolean
    get() = true
  override val speakerAvatarUrl: String
    get() = baseUrl + "speaker/images/"
  override val baseUrl: String
    get() =
        "https://programm.javaland.eu/${getYear()}/rest/" // https://latest.dukecon.org/javaland/2018/rest/") //endpoitUrlProvider.getUrl())

  private fun getYear(): String {
    return "2019" // Calendar.getInstance().get(Calendar.YEAR).toString()
  }

  override val conferenceId: String
    get() = "javaland${getYear()}"
}
