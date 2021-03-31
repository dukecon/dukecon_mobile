package org.dukecon.android.ui.configuration

import android.app.Application
import org.dukecon.data.source.ConferenceConfiguration

object ConfigurationFactory {
  fun createConfiguration(application: Application): ConferenceConfiguration = BedconConfiguration
}
