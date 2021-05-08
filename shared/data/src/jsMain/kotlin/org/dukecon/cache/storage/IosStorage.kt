package org.dukecon.cache.storage


actual class ApplicationContext

actual fun applicationStorageFactory(context: ApplicationContext?): ApplicationStorage =
    JsStorage()

internal class JsStorage : ApplicationStorage {


  override fun putBoolean(key: String, value: Boolean) {
  //  delegate.setBool(value, key)
  }

  override fun getBoolean(key: String, defaultValue: Boolean): Boolean = false
      //if (hasKey(key)) delegate.boolForKey(key) else defaultValue

  override fun putString(key: String, value: String) {
    //delegate.setObject(value, key)
  }

  override fun getString(key: String): String? = "" //delegate.stringForKey(key)

  private fun hasKey(key: String): Boolean = false // delegate.objectForKey(key) != null
}
