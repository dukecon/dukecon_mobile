package org.dukecon.remote.api

class ImmutableList<T>(private val inner:List<T>) : List<T> by inner
class ImmutableMap<K, V>(private val inner: Map<K, V>) : Map<K, V> by inner

fun <K, V> Map<K, V>.toImmutableMap(): Map<K, V> {
    if (this is ImmutableMap<K, V>) {
        return this
    } else {
        return ImmutableMap(this)
    }
}

fun <T> List<T>.toImmutableList(): List<T> {
    if (this is ImmutableList<T>) {
        return this
    } else {
        return ImmutableList(this)
    }
}