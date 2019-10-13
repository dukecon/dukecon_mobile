package org.dukecon.domain.model

data class OAuthToken(
        val accessToken: String,
        val refreshToken: String,
        val expiresAt: Long)