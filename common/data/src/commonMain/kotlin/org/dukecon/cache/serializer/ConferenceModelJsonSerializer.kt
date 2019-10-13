package org.dukecon.cache.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.dukecon.cache.model.ConferenceModel
import org.dukecon.cache.storage.ApplicationStorage
import kotlin.native.concurrent.ThreadLocal
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@ThreadLocal
object ConferenceModelJsonSerializer {

    private val storage: ApplicationStorage = ApplicationStorage()

    var conference: ConferenceModel by storage(ConferenceModel.serializer()) { ConferenceModel() }


    @UseExperimental(UnstableDefault::class)
    inline operator fun <reified T> ApplicationStorage.invoke(
            serializer: KSerializer<T>,
            crossinline block: () -> T
    ): ReadWriteProperty<ConferenceModelJsonSerializer, T> = object : ReadWriteProperty<ConferenceModelJsonSerializer, T> {
        private var currentValue: T? = null

        override fun setValue(thisRef: ConferenceModelJsonSerializer, property: KProperty<*>, value: T) {
            val key = property.name
            currentValue = value
            putString(key, Json.stringify(serializer, value))
        }

        override fun getValue(thisRef: ConferenceModelJsonSerializer, property: KProperty<*>): T {
            currentValue?.let { return it }

            val key = property.name

            val result = try {
                getString(key)?.let { Json.parse(serializer, it) }
            } catch (cause: Throwable) {
                null
            } ?: block()

            currentValue = result
            return result
        }
    }
}