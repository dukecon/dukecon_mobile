package org.dukecon.aspects.logging


actual fun log(level: LogLevel, tag: String, message: String) {
//  LOGGER.debug(tag + "===" + message)
}

actual fun log(level: LogLevel, tag: String, message: String, error: Throwable) {
  //LOGGER.error(tag + "===" + message, error)
}
