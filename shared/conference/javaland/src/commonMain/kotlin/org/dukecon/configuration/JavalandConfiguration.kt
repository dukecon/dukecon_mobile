package org.dukecon.configuration

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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
    val currentMoment = Clock.System.now()
    val datetimeInUtc = currentMoment.toLocalDateTime(TimeZone.UTC)
    return datetimeInUtc.year.toString()
  }

  override val conferenceId: String
    get() = "javaland${getYear()}"
}
