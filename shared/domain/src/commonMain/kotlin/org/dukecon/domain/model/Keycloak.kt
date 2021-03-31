package org.dukecon.domain.model

data class Keycloak(
    val realm: String,
    val authServerUrl: String,
    val sslRequired: String,
    val resource: String,
    val redirectUri: String,
    val useAccountManagement: Boolean
)
