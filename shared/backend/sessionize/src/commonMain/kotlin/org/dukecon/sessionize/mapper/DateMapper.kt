package org.dukecon.sessionize.mapper

import io.ktor.util.date.GMTDate
import io.ktor.util.date.Month

fun Int.toString(minSize: Int): String = "$this".padStart(minSize, '0')

/** According to mask: "yyyy-MM-dd'T'HH:mm:ss" */
fun String.parseDate(): GMTDate {
  val year = substring(0, 4).toIntOrFormatError()
  val month = substring(5, 7).toIntOrFormatError()
  val day = substring(8, 10).toIntOrFormatError()

  val hour = substring(11, 13).toIntOrFormatError()
  val minute = substring(14, 16).toIntOrFormatError()
  val second = substring(17, 19).toIntOrFormatError()

  return GMTDate(second, minute, hour, day, Month.from(month - 1), year)
}

fun String.toIntOrFormatError() = toIntOrNull() ?: throw Error("Format of $this is not correct")

fun GMTDate.parseToString(): String {
  val monthPart = "${month.ordinal + 1}".padStart(2, '0')
  return "$year-$monthPart-${dayOfMonth.toString(2)}T${hours.toString(2)}:${minutes.toString(2)}:${seconds.toString(2)}"
}
