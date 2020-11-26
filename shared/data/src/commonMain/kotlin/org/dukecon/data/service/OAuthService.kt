package org.dukecon.data.service

import org.dukecon.data.model.OAuthToken

interface OAuthService {
    fun code2token(code: String): OAuthToken
    fun refresh(token: String): OAuthToken
}