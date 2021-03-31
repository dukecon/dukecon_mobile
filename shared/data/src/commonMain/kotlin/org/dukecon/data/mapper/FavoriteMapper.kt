package org.dukecon.data.mapper

import org.dukecon.data.model.FavoriteEntity
import org.dukecon.domain.model.Favorite
import org.dukecon.domain.model.Speaker

/**
 * Map a [FavoriteEntity] to and from a [Favorite] instance when data is moving between this later
 * and the Domain layer
 */
open class FavoriteMapper constructor() : Mapper<FavoriteEntity, Favorite> {

  /** Map a [FavoriteEntity] instance to a [Favorite] instance */
  override fun mapFromEntity(type: FavoriteEntity): Favorite {
    return Favorite(type.id, type.version, true)
  }

  /** Map a [Speaker] instance to a [FavoriteEntity] instance */
  override fun mapToEntity(type: Favorite): FavoriteEntity {
    return FavoriteEntity(type.id, type.version)
  }
}
