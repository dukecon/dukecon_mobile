package org.dukecon.presentation.model

/**
 * Representation for a [SpeakerView] instance for this layers Model representation
 */
data class SpeakerDetailView(val id: String, val name: String, val title: String, val twitter: String,
                             val twitterHandle: String, val bio: String, val website: String, val avatar: String) {

}