package org.dukecon.android.ui.features.login

import org.dukecon.domain.features.oauth.TokensStorage
import org.dukecon.domain.model.OAuthToken

class SettingsTokenStorage: TokensStorage {
    override fun clear() {
        //settings.clear("token")
    }

    override fun getToken(): OAuthToken? {
        return null
    }

    override fun loginRequired(): Boolean {
        return true
    }

    override fun setToken(refreshedToken: OAuthToken) {
    }
}

private fun String.toOAuthToken(): OAuthToken {
    return OAuthToken("", "", 0)
}
