package org.dukecon.data.mapper

import org.dukecon.cache.model.EventModel
import org.dukecon.data.model.EventEntity
import org.dukecon.domain.model.*

open class EventMapper {

  fun mapFromEntity(
      type: EventEntity,
      speakers: List<Speaker>,
      favorites: List<Favorite>,
      metaData: MetaData
  ): Event {
    return Event(
        type.id,
        type.title,
        type.abstractText,
        type.startTime,
        type.endTime,
        createSpeakerList(type.speakerIds, speakers),
        mapFavorite(type.id, favorites),
        mapRoomId(type.locationId, metaData.locations),
        mapTrack(type.trackId, metaData.tracks),
        mapAudience(type.audienceId, metaData.audiences),
        mapEventType(type.eventTypeId, metaData.eventTypes),
        type.demo,
        mapLanguage(type.languageId, metaData.languages),
        type.simultan,
        type.veryPopular,
        type.fullyBooked,
        type.numberOfFavorites)
  }

  private fun mapLanguage(languageId: String, languages: List<Language>): Language {
    return languages.find { it.id == languageId } ?: Language("", "", 0, emptyMap(), "")
  }

  private fun mapEventType(eventTypeId: String, eventTypes: List<EventType>): EventType {
    return eventTypes.find { it.id == eventTypeId } ?: EventType("", 0, emptyMap(), "")
  }

  private fun mapAudience(audienceId: String, audiences: List<Audience>): Audience {
    return audiences.find { it.id == audienceId } ?: Audience("", 0, emptyMap(), "")
  }

  private fun mapTrack(trackId: String, tracks: List<Track>): Track {
    return tracks.find { it.id == trackId } ?: Track("", 0, emptyMap(), "")
  }

  private fun mapFavorite(id: String, favorites: List<Favorite>): Favorite {
    return favorites.firstOrNull { it.id == id } ?: Favorite("0", 0, false)
  }

  private fun mapRoomId(locationId: String, rooms: List<Location>): Location {
    return rooms.find { it.id == locationId } ?: Location("", 0, emptyMap(), "", 0)
  }

  private fun createSpeakerList(speakerIds: List<String>, speakers: List<Speaker>): List<Speaker> {
    val result: MutableList<Speaker> = mutableListOf()
    speakerIds.forEach {
      val speaker = speakers.find { speaker -> it == speaker.id }
      if (speaker != null) {
        result.add(
            Speaker(
                it,
                name = speaker.name,
                title = speaker.title,
                twitter = speaker.twitter,
                bio = speaker.bio,
                website = speaker.website,
                avatar = speaker.avatar))
      }
    }
    return result
  }

  /** Map a [Event] instance to a [EventModel] instance */
  fun mapToEntity(type: Event): EventEntity {
    return EventEntity(
        type.eventId,
        type.title,
        type.eventDescription,
        type.startTime,
        type.endTime,
        createIdsList(type.speakers),
        type.room.id,
        type.language.id,
        type.track.id,
        type.audience.id,
        type.eventType.id,
        type.demo,
        type.simultan,
        type.veryPopular,
        type.fullyBooked,
        type.numberOfFavorites)
  }

  private fun createIdsList(speakerIds: List<Speaker>): List<String> {
    val result: MutableList<String> = mutableListOf()
    speakerIds.forEach { run { result.add(it.id) } }
    return result
  }
}
