package org.dukecon.android.ui.configuration

import android.app.Application
import org.dukecon.data.source.OAuthConfiguration

class JavalandOAuthConfiguration(val application: Application) : OAuthConfiguration {
    override val redirectUri: String
        get() = "appdukecon://redirect2token"
    override val baseUrl: String
        get() = "https://programm.javaland.eu/auth/realms/javaland/protocol/openid-connect/"
    override val clientId: String
        get() = "dukecon_mobile"
}
