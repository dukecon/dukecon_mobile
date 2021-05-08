package org.dukecon.cache.storage

actual class ApplicationContext()

@Suppress("FunctionName")
actual fun applicationStorageFactory(context: ApplicationContext?): ApplicationStorage =
    InMemoryStorage()
