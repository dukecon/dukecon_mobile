package org.dukecon.android.ui.features.timemachine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * An [BroadcastReceiver] subclass for handling calls via adb, e.g changing current date and time
 *
 * helper methods.
 */
class SetCustomdateTimeReceiver : BroadcastReceiver() {

  lateinit var currentTimeProvider: CustomizableCurrentTimeProvider
  override fun onReceive(context: Context?, intent: Intent?) {

    if (context != null) {
      if (intent != null) {
        val action = intent.action
        if ("org.dukecon.android.ui.intent.TIME" == action) {
          val extras = intent.extras
          if (extras != null) {
            handleSendText(extras) // Handle text being sent
          }
        }
      }
    }
  }

  /**
   * * adb shell am broadcast -a org.dukecon.android.ui.intent.TIME --es set_time "12:33:00 "
   * @param intent
   */
  private fun handleSendText(intent: Bundle) {
    val sharedText = intent.getString("set_time")
    if (sharedText != null) {
      try {
        // val instant = OffsetDateTime.parse(sharedText).toInstant()
        val time = 1L
        currentTimeProvider.setCustomMillis(time)
      } catch (e: IllegalArgumentException) {
        // ignore
      }
    }
  }
}
