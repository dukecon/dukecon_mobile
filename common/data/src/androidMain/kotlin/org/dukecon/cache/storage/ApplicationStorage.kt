package org.dukecon.cache.storage

import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager

@SuppressLint("StaticFieldLeak")
var appAndroidContext: Context? = null

actual fun ApplicationStorage(): ApplicationStorage = AndroidStorage

object AndroidStorage : ApplicationStorage {
    private val sharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(appAndroidContext)
    }

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
            sharedPreferences.getBoolean(key, defaultValue)

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? = sharedPreferences.getString(key, null)
}