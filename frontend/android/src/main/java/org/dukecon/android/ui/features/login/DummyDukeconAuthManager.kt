package org.dukecon.android.ui.features.login

import org.dukecon.domain.aspects.auth.AuthManager
import org.dukecon.domain.model.OAuthToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DummyDukeconAuthManager @Inject constructor() : AuthManager {
    override fun hasSession(token: OAuthToken?): Boolean {
        return false
    }

    override suspend fun exchangeToken(code: String) {

    }

    override suspend fun login(activity: Any) {

    }
}
