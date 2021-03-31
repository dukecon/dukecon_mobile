package org.dukecon.domain.model

data class EventType(
    val id: String = "",
    val order: Int = -1,
    val names: Map<String, String> = emptyMap(),
    val icon: String = ""
)
