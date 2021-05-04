package org.dukecon.aspects.logging


actual fun log(level: LogLevel, tag: String, message: String) {
    console.log("[LOG] $level $tag $message")
}

actual fun log(level: LogLevel, tag: String, message: String, error: Throwable) {
    console.log("[LOG] $level $tag $message")
}
