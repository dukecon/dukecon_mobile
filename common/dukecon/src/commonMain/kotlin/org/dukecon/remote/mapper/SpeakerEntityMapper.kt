package org.dukecon.remote.mapper

import org.dukecon.data.model.SpeakerEntity
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.remote.api.Speaker

/**
 * Map a [Speaker] to and from a [SpeakerEntity] instance when data is moving between
 * this later and the Data layer
 */
open class SpeakerEntityMapper constructor(private val conferenceConfiguration: ConferenceConfiguration,
                                           private val twitterLinkMapper: TwitterUrlMapper) : EntityMapper<Speaker, SpeakerEntity> {

    /**
     * Map an instance of a [Speaker] to a [SpeakerEntity] model
     */
    override fun mapFromRemote(type: Speaker): SpeakerEntity {
        return SpeakerEntity(
                type.id,
                type.name,
                type.company,
                twitterLinkMapper.getNormalizedTwitterUrl(type.twitter),
                type.bio,
                type.website,
                getAvatarUrlFromId(type.photoId)
        )
    }

    private fun getAvatarUrlFromId(s: String): String {
        return conferenceConfiguration.speakerAvatarUrl + s
    }
}