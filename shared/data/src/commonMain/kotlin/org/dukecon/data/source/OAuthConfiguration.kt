package org.dukecon.data.source

interface OAuthConfiguration {
    val baseUrl: String
    val clientId: String
    val redirectUri: String
}