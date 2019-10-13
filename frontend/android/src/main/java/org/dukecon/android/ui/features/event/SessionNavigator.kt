package org.dukecon.android.ui.features.event

import org.dukecon.presentation.model.EventView

interface SessionNavigator {
    fun showSession(session: EventView)
}