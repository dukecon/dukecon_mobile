package org.dukecon.data.model


/**
 * Representation for a [SpeakerEntity] fetched from an external layer data source
 */

data class SpeakerEntity(
        val id: String,
        val name: String,
        val title: String,
        val twitter: String,
        val bio: String,
        val website: String,
        val avatar: String
)

