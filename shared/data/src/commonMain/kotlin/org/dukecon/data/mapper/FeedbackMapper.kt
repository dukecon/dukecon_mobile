package org.dukecon.data.mapper

import org.dukecon.data.model.FeedbackEntity
import org.dukecon.domain.model.Feedback

/**
 * Map a [FeedbackEntity] to and from a [Feedback] instance when data is moving between this later
 * and the Domain layer
 */
open class FeedbackMapper : Mapper<FeedbackEntity, Feedback> {

  /** Map a [FeedbackEntity] instance to a [Feedback] instance */
  override fun mapFromEntity(type: FeedbackEntity): Feedback {
    return Feedback(type.sessionId, type.value, type.comment)
  }

  /** Map a [Feedback] instance to a [FeedbackEntity] instance */
  override fun mapToEntity(type: Feedback): FeedbackEntity {
    return FeedbackEntity(type.sessionId, type.value, type.comment)
  }
}
