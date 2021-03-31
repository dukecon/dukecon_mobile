package org.dukecon.data.mapper

import org.dukecon.data.model.KeycloakEntity
import org.dukecon.domain.model.Keycloak

open class KeycloakMapper : Mapper<KeycloakEntity, Keycloak> {

  override fun mapFromEntity(type: KeycloakEntity): Keycloak {
    return Keycloak(
        type.realm,
        type.authServerUrl,
        type.sslRequired,
        type.resource,
        type.redirectUri,
        type.useAccountManagement)
  }

  override fun mapToEntity(type: Keycloak): KeycloakEntity {
    return KeycloakEntity(
        type.realm,
        type.authServerUrl,
        type.sslRequired,
        type.resource,
        type.redirectUri,
        type.useAccountManagement)
  }
}
