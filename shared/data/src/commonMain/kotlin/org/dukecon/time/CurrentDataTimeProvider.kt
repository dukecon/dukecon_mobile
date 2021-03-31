package org.dukecon.time

interface CurrentDataTimeProvider {
  fun currentTimeMillis(): Long
}
