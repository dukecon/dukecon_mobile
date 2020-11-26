package org.dukecon.data.mapper

import org.dukecon.aspects.twitter.TwitterLinks
import org.dukecon.cache.model.SpeakerModel
import org.dukecon.data.model.SpeakerEntity
import org.dukecon.domain.model.Speaker

/**
 * Map a [SpeakerModel] to and from a [Speaker] instance when data is moving between
 * this later and the Domain layer
 */
open class SpeakerMapper constructor(private val twitterLinks: TwitterLinks) : Mapper<SpeakerEntity, Speaker> {

    /**
     * Map a [SpeakerModel] instance to a [Speaker] instance
     */
    override fun mapFromEntity(type: SpeakerEntity): Speaker {
        return Speaker(type.id, type.name, type.title, twitterLinks.getNormalizedTwitterUrl(type.twitter),
                type.bio, type.website, type.avatar)
    }

    /**
     * Map a [Speaker] instance to a [SpeakerModel] instance
     */
    override fun mapToEntity(type: Speaker): SpeakerEntity {
        return SpeakerEntity(type.id, type.name, type.title, type.twitter, type.bio, type.website, type.avatar)
    }
}