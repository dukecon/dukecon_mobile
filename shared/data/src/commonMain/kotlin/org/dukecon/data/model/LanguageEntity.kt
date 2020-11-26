package org.dukecon.data.model

data class LanguageEntity(val id: String, val code: String, val order: Int,
                          val names: Map<String, String>, val icon: String)
