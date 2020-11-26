package org.dukecon.presentation.feature.feedback

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.dukecon.domain.model.Feedback
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.presentation.CoroutinePresenter

open class FeedbackPresenter(private val conferenceRepository: ConferenceRepository) :
        CoroutinePresenter<FeedbackMvp.View>(), FeedbackMvp.Presenter {
    override fun showError(error: Throwable) {
        view?.dismiss()
    }

    private var view: FeedbackMvp.View? = null
    private var sessionId: String? = null

    override fun onAttach(view: FeedbackMvp.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun setSessionId(sessionId: String) {
        this.sessionId = sessionId
    }

    override fun submit(overall: Int, comment: String) {
        val sessionId = this.sessionId
        if (sessionId != null) {
            launch {
                // TODO MPP
                /*
                withContext(getIoContext()) {
                    conferenceRepository.submitFeedback(Feedback(sessionId, overall, comment))
                }
                view?.dismiss()

                 */
            }
        }
    }
}