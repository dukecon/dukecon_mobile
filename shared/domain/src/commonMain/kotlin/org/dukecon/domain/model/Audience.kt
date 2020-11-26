package org.dukecon.domain.model

data class Audience(val id: String = "", val order: Int = -1, val names: Map<String, String> = emptyMap(), val icon: String = "")
