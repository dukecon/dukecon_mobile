package org.dukecon.domain.model

enum class TargetHost {
    COMMON,
    ANDROID,
    IOS
}

data class Library(val name: String, val owner: String, val license: String, val url: String,
                   val targetHost: TargetHost = TargetHost.ANDROID)