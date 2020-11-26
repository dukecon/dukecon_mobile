package org.dukecon.cache.model

import kotlinx.serialization.Serializable

@Serializable
data class SpeakerModel(
        val id: String,
        val name: String,
        val title: String,
        val twitter: String,
        val bio: String,
        val website: String,
        val avatar: String
)

