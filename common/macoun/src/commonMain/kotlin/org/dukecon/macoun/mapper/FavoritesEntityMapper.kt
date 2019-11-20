package org.dukecon.macoun.mapper

import org.dukecon.data.model.FavoriteEntity
import org.dukecon.domain.model.Favorite

class FavoritesEntityMapper constructor() {
    fun mapFromRemote(type: Favorite): FavoriteEntity {
        return FavoriteEntity(type.id, type.version)
    }

    fun mapToRemote(type: FavoriteEntity): Favorite {
        return Favorite(
                id = type.id,
                version = type.version,
                selected = true)
    }
}