package org.dukecon.android.ui.features.login

import io.ktor.http.auth.HttpAuthHeader.Parameters.OAuthToken
import org.dukecon.domain.features.oauth.TokensStorage
import org.dukecon.domain.model.OAuthToken
import javax.inject.Inject

class SettingsTokenStorage @Inject constructor() : TokensStorage {
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
