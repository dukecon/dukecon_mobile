package org.dukecon.presentation.mapper

import org.dukecon.domain.model.Speaker
import org.dukecon.presentation.model.EventView
import org.dukecon.presentation.model.SpeakerView

/**
 * Map a [SpeakerView] to and from a [Speaker] instance when data is moving between
 * this layer and the Domain layer
 */
class SpeakerMapper: Mapper<SpeakerView, Speaker> {

    /**
     * Map a [Speaker] instance to a [EventView] instance
     */
    override fun mapToView(type: Speaker): SpeakerView {
        return SpeakerView(type.id, type.name, type.title, type.avatar)
    }
}
