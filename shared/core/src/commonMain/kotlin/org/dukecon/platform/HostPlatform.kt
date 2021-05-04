package org.dukecon.platform

enum class HostPlatform {
  COMMON,
  ANDROID,
  IOS,
  WEB
}

expect fun getTargetHost(): HostPlatform
