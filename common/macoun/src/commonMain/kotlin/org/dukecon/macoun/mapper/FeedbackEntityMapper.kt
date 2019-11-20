package org.dukecon.macoun.mapper

import org.dukecon.data.model.FeedbackEntity
import org.dukecon.macoun.api.Feedback

class FeedbackEntityMapper constructor() {

    fun mapToRemote(type: FeedbackEntity): Feedback {
        return Feedback(comment = type.comment,
                rating = type.value)
    }
}