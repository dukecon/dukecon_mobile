package org.dukecon.configuration

import org.dukecon.data.source.ConferenceConfiguration

object ConfigurationFactory {
    fun createConfiguration(): ConferenceConfiguration =
            JavalandConfiguration()
}
