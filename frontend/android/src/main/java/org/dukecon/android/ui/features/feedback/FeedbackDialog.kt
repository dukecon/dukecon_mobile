package org.dukecon.android.ui.features.feedback

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.dialog_feedback.view.*
import org.dukecon.android.ui.R
import org.dukecon.core.IoCProvider
import org.dukecon.presentation.feature.feedback.FeedbackMvp

class FeedbackDialog(context: Context, private val sessionId: String) :
    Dialog(context, true, null), FeedbackMvp.View {
  override fun showError(throwable: Throwable) {}

  private val presenter: FeedbackMvp.Presenter by lazy { IoCProvider.get<FeedbackMvp.Presenter>() }

  lateinit var view: View

  @SuppressLint("InflateParams")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter.setSessionId(sessionId)

    view = LayoutInflater.from(context).inflate(R.layout.dialog_feedback, null, false)
    view.feedback_title.setText(R.string.feedback_title)
    view.submit.setOnClickListener {
      // view.overall.rating.toInt()
      presenter.submit(getRating(), view.comment.editableText.toString())
    }

    view.cancel.setOnClickListener { cancel() }

    setContentView(view)
  }

  private fun getRating(): Int {
    when (view.rating.checkedRadioButtonId) {
      R.id.good -> return 1
      R.id.ok -> return 2
      R.id.bad -> return 3
      else -> { // Note the block
        return 0
      }
    }
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.onAttach(this)
  }

  override fun onDetachedFromWindow() {
    presenter.onDetach()
    super.onDetachedFromWindow()
  }
}
