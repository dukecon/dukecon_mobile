package org.dukecon.cache.storage

actual class ApplicationContext()

@Suppress("FunctionName")
actual fun applicationStorageFactory(context: ApplicationContext?): ApplicationStorage = InMemoryStorage()

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
