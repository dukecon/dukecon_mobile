package org.dukecon.configuration

import org.dukecon.data.source.OAuthConfiguration

class JavalandOAuthConfiguration() : OAuthConfiguration {
  override val redirectUri: String
    get() = "appdukecon://redirect2token"
  override val baseUrl: String
    get() = "https://programm.javaland.eu/auth/realms/javaland/protocol/openid-connect/"
  override val clientId: String
    get() = "dukecon_mobile"
}
