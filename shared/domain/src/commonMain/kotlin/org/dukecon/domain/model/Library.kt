package org.dukecon.domain.model

import org.dukecon.platform.HostPlatform

data class Library(
    val name: String,
    val owner: String,
    val license: String,
    val url: String,
    val targetHost: HostPlatform = HostPlatform.ANDROID
)
