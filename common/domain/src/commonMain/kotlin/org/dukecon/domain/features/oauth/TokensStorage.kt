package org.dukecon.domain.features.oauth

import org.dukecon.domain.model.OAuthToken

interface TokensStorage {
    fun getToken(): OAuthToken?
    fun loginRequired(): Boolean
    fun clear()
    fun setToken(refreshedToken: OAuthToken)
}