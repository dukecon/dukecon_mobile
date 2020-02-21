package org.dukecon.macoun.mapper

import io.ktor.util.date.GMTDate
import io.ktor.util.date.Month
import org.dukecon.data.model.EventEntity
import org.dukecon.macoun.api.Talk

open class EventEntityMapper : EntityMapper<Talk, EventEntity> {

    override fun mapFromRemote(type: Talk): EventEntity {
        return EventEntity(
                id = type.id,
                title = type.title,
                abstractText = type.description,
                startTime = type.startdate.parseMacounDate(),
                endTime = type.startdate.parseMacounDate().addMinutes(type.duration),
                speakerIds = type.speaker_id,
                locationId = type.room_id,
                languageId = "",
                trackId = "", //type.category,
                audienceId = "",
                eventTypeId = "",
                demo = false,
                simultan = false,
                veryPopular = false,
                fullyBooked = false,
                numberOfFavorites = 0)
    }
}

private fun GMTDate.addMinutes(duration: Int): GMTDate {
    return GMTDate(this.timestamp + duration * 1000 * 60)
}

/**
 * 2019-10-04 10:30
 * According to mask: "yyyy-MM-dd HH:mm"
 */
private fun String.parseMacounDate(): GMTDate {
    val year = substring(0, 4).toIntOrFormatError()
    val month = substring(5, 7).toIntOrFormatError()
    val day = substring(8, 10).toIntOrFormatError()

    val hour = substring(11, 13).toIntOrFormatError()
    val minute = substring(14, 16).toIntOrFormatError()
    val second = 0
    return GMTDate(second, minute, hour, day, Month.from(month - 1), year)
}
