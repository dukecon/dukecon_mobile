package org.dukecon.domain.aspects.auth

import org.dukecon.domain.model.OAuthToken

interface AuthManager {
    suspend fun login(activity: Any)
    suspend fun exchangeToken(code: String)
    fun hasSession(token: OAuthToken?): Boolean
}