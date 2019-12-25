package org.dukecon.macoun.mapper

import org.dukecon.data.model.KeycloakEntity
import org.dukecon.macoun.api.Keycloak

class KeycloakEntityMapper() : EntityMapper<Keycloak, KeycloakEntity> {

    override fun mapFromRemote(type: Keycloak): KeycloakEntity {
        return KeycloakEntity(
                type.realm,
                type.auth_server_url,
                type.ssl_required,
                type.resource,
                type.redirectUri,
                type.useAccountManagement.toBoolean()
        )
    }
}