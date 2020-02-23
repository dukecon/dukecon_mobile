package org.dukecon.remote.mapper

import org.dukecon.data.model.SpeakerEntity
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.remote.api.Speaker

/**
 * Map a [Speaker] to and from a [SpeakerEntity] instance when data is moving between
 * this later and the Data layer
 */
internal open class SpeakerEntityMapper constructor(private val conferenceConfiguration: ConferenceConfiguration,
                                                    private val twitterLinkMapper: TwitterUrlMapper) : EntityMapper<Speaker, SpeakerEntity> {

    /**
     * Map an instance of a [Speaker] to a [SpeakerEntity] model
     */
    override fun mapFromRemote(type: Speaker): SpeakerEntity {
        return SpeakerEntity(
                id = type.id ?: "",
                name = type.name ?: "",
                title = type.company ?: "",
                twitter = twitterLinkMapper.getNormalizedTwitterUrl(type.twitter ?: ""),
                bio = type.bio ?: "",
                website = type.website ?: "",
                avatar = getAvatarUrlFromId(type.photoId ?: "")
        )
    }

    private fun getAvatarUrlFromId(s: String): String {
        return conferenceConfiguration.speakerAvatarUrl + s
    }
}