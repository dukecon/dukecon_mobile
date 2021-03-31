package org.dukecon.cache.storage

import android.content.Context
import android.preference.PreferenceManager

actual class ApplicationContext(val appAndroidContext: Context)

actual fun applicationStorageFactory(context: ApplicationContext?): ApplicationStorage =
    AndroidApplicationStorage(context)

class AndroidApplicationStorage(context: ApplicationContext?) : ApplicationStorage {

  private val sharedPreferences by lazy {
    PreferenceManager.getDefaultSharedPreferences(context?.appAndroidContext)
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
