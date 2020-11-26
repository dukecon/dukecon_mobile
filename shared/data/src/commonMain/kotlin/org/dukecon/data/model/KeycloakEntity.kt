package org.dukecon.data.model

data class KeycloakEntity(
    val realm: String, val authServerUrl: String, val sslRequired: String, val resource: String,
    val redirectUri: String, val useAccountManagement: Boolean
)