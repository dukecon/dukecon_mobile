package org.dukecon.date

actual fun now(): Long {
  return System.currentTimeMillis()
}
