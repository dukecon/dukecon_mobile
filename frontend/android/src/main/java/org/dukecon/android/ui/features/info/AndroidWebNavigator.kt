package org.dukecon.android.ui.features.info

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.dukecon.presentation.feature.info.WebNavigator

class AndroidWebNavigator(private val context: Context) : WebNavigator {

    override fun navigateToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}