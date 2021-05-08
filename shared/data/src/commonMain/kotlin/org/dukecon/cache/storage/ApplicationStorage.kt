package org.dukecon.cache.storage

expect class ApplicationContext

@Suppress("FunctionName")
expect fun applicationStorageFactory(context: ApplicationContext?): ApplicationStorage

interface ApplicationStorage {
  fun putBoolean(key: String, value: Boolean)
  fun getBoolean(key: String, defaultValue: Boolean): Boolean
  fun putString(key: String, value: String)
  fun getString(key: String): String?
}

class InMemoryStorage : ApplicationStorage {

  private val booleans: HashMap<String, Boolean> = HashMap()
  private val strings: HashMap<String, String> = HashMap()

  override fun putBoolean(key: String, value: Boolean) {
    booleans[key] = value
  }

  override fun getBoolean(key: String, defaultValue: Boolean): Boolean = booleans[key] ?: false

  override fun putString(key: String, value: String) {
    strings[key] = value
  }

  override fun getString(key: String): String? = strings[key] ?: ""
}
