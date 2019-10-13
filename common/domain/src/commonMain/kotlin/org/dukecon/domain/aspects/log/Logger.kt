package org.dukecon.domain.aspects.log

sealed class LogLevel {
    object DEBUG : LogLevel()
    object INFO : LogLevel()
    object WARN : LogLevel()
    object ERROR : LogLevel()
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect fun log(level: LogLevel, tag: String, message: String)

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect fun log(level: LogLevel, tag: String, message: String, error: Throwable)

