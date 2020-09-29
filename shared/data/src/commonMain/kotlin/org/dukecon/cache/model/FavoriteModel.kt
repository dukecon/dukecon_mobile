package org.dukecon.cache.model

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteModel(val id: String, val version: Int)