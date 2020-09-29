package org.dukecon.cache.storage

expect class ApplicationContext

@Suppress("FunctionName")
expect fun ApplicationStorage(context: ApplicationContext?): ApplicationStorage

interface ApplicationStorage {
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun putString(key: String, value: String)
    fun getString(key: String): String?
}
