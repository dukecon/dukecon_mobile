package org.dukecon.macoun.mapper

import org.dukecon.data.model.RoomEntity
import org.dukecon.macoun.api.Location

class RoomEntityMapper() : EntityMapper<Location, RoomEntity> {

    override fun mapFromRemote(type: Location): RoomEntity {
        return RoomEntity(type.id, type.name)
    }
}