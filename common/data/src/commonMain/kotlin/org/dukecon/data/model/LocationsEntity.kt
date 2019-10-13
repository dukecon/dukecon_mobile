package org.dukecon.data.model

data class LocationsEntity(
        val id: String,
        val order: Int,
        val names: Map<String, String>,
        val icon: String,
        val capacity: Int
)
