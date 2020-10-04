package org.dukecon.platform


enum class HostPlatform {
    COMMON,
    ANDROID,
    IOS
}

expect fun getTargetHost():HostPlatform