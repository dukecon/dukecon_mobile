package org.dukecon.domain.model

data class Language(val id: String, val code: String, val order: Int,
                    val names: Map<String, String>, val icon: String)
