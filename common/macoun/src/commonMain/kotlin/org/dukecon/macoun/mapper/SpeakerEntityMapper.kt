package org.dukecon.macoun.mapper

import org.dukecon.data.model.SpeakerEntity
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.macoun.api.Speaker

open class SpeakerEntityMapper constructor(private val conferenceConfiguration: ConferenceConfiguration,
                                           private val twitterLinkMapper: TwitterLinks) : EntityMapper<Speaker, SpeakerEntity> {

    override fun mapFromRemote(type: Speaker): SpeakerEntity {
        return SpeakerEntity(
                id = type.id,
                name = "${type.firstname} ${type.lastname}",
                title = type.twitter,
                twitter = twitterLinkMapper.getNormalizedTwitterUrl(type.twitter),
                bio = type.description,
                website = "",
                avatar = getAvatarUrlFromId(type.image)
        )
    }

    private fun getAvatarUrlFromId(s: String): String {
        return conferenceConfiguration.speakerAvatarUrl + s
    }
}