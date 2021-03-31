package org.dukecon.remote.store

import org.dukecon.aspects.twitter.TwitterLinks
import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceRemote
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.remote.api.MacounApi
import org.dukecon.remote.mapper.SpeakerEntityMapper

class MacounConferenceRemote(
    private val dukeconApi: MacounApi,
    conferenceConfiguration: ConferenceConfiguration
) : ConferenceRemote {

  private val speakerEntityMapper = SpeakerEntityMapper(conferenceConfiguration, TwitterLinks())
  /*    private val eventEntityMapper = EventEntityMapper()
     private val speakerEntityMapper =
         SpeakerEntityMapper(conferenceConfiguration, twitterLinkMapper)
     private val metaDataEntityMapper = MetaDataEntityMapper()
     private val roomEntityMapper = RoomEntityMapper()

  */

  override suspend fun getRooms(): List<RoomEntity> = emptyList()

  override suspend fun getEvents(): List<EventEntity> = emptyList()

  override suspend fun getSpeakers(): List<SpeakerEntity> {
    return dukeconApi.getFahrplan().speakers?.let { speakers ->
      speakers.map { speakerEntityMapper.mapFromRemote(it) }
    }
        ?: emptyList()
  }

  override suspend fun getMetaData(): MetaDataEntity =
      MetaDataEntity(
          id = "",
          audiences = emptyList(),
          eventTypes = emptyList(),
          languages = emptyList(),
          defaultLanguage = LanguageEntity(id = "", code = "", order = 0, names = emptyMap(), ""),
          tracks = emptyList(),
          defaultIcon = "",
          locations = emptyList())

  override fun submitFeedback(feedback: FeedbackEntity) {}

  override fun getFavorites(): List<FavoriteEntity> {
    return emptyList()
  }

  override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
    TODO("not supported without oauth")
  }
}
