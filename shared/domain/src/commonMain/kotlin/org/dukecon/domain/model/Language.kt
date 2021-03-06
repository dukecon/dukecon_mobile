package org.dukecon.domain.model

data class Language(val id: String = "", val code: String = "", val order: Int = -1,
                    val names: Map<String, String> = emptyMap(), val icon: String = "")
