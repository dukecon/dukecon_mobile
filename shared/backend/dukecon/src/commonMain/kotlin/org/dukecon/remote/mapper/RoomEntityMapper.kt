package org.dukecon.remote.mapper

import org.dukecon.data.model.RoomEntity
import org.dukecon.remote.models.Location

/**
 * Map a [Location] to and from a [RoomEntity] instance when data is moving between this later and
 * the Data layer
 */
internal class RoomEntityMapper() : EntityMapper<Location, RoomEntity> {

  override fun mapFromRemote(type: Location): RoomEntity {
    return RoomEntity(type.id ?: "", type.names?.let { a -> a["de"] } ?: "")
  }
}
