package org.dukecon.presentation.mapper

import org.dukecon.domain.aspects.twitter.TwitterLinks
import org.dukecon.domain.model.Speaker
import org.dukecon.presentation.model.SpeakerDetailView

/**
 * Map a [SpeakerDetailView] to and from a [Speaker] instance when data is moving between
 * this layer and the Domain layer
 */
open class SpeakerDetailMapper constructor(val twitterLinks: TwitterLinks) : Mapper<SpeakerDetailView, Speaker> {

    /**
     * Map a [Speaker] instance to a [SpeakerDetailView] instance
     */
    override fun mapToView(type: Speaker): SpeakerDetailView {
        return SpeakerDetailView(type.id, type.name, type.title, type.twitter, twitterLinks.getHandle(type.twitter),
                type.bio, type.website, type.avatar)
    }
}