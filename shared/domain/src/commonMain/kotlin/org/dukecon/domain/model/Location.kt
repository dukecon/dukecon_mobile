package org.dukecon.domain.model

data class Location(
    val id: String = "",
    val order: Int = -1,
    val names: Map<String, String> = emptyMap(),
    val icon: String = "",
    val capacity: Int = 0
)
