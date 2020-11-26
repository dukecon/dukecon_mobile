package org.dukecon.data.model

data class OAuthToken(val accessToken: String, val refreshToken: String, val exppiresIn: Long)