package org.dukecon.plugin

import java.io.File

object FileLinecCounter {
    fun loc(fileName: String): Long {
        var i = 0L
        File(fileName).readLines().forEach {
            if (it.isNotBlank()) {
                i++
            }
        }
        return i
    }
}