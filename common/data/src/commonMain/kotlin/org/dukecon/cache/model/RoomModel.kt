package org.dukecon.cache.model

import kotlinx.serialization.Serializable

/**
 * Representation for a [RoomModel] fetched from an external layer data source
 */

@Serializable
data class RoomModel(val id: String, val name: String)
