package org.dukecon.domain.model

data class Track(val id: String = "", val order: Int = -1, val names: Map<String, String> = emptyMap(), val icon: String = "")