package org.dukecon.date

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual fun now(): Long = (NSDate().timeIntervalSince1970 * 1000).toLong()
