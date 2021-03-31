package org.dukecon.cache.model

import kotlinx.serialization.Serializable

@Serializable data class MetaDataModel(val id: String, val locations: List<LocationsModel>)

@Serializable
data class LocationsModel(
    val id: String,
    val order: Int,
    val names: Map<String, String>,
    val icon: String,
    val capacity: Int
)
