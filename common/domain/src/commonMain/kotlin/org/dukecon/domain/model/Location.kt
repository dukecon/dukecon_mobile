package org.dukecon.domain.model

data class Location(val id: String, val order: Int, val names: Map<String, String>,
                    val icon: String, val capacity: Int)
