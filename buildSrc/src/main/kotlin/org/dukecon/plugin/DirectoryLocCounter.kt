package org.dukecon.plugin

import java.io.File

object DirectoryLocCounter {


    fun loc(dir: String, includeExtensions: List<String>):Long {
        return readDirectory(dir, includeExtensions)
    }

    private fun readDirectory(dir: String, includeExtensions: List<String>): Long {
        var loc = 0L
        for (file: File? in File(dir).listFiles()) {
            file?.let { it ->
                if (it.isDirectory) {
                    loc += readDirectory(file.path, includeExtensions)
                } else {
                    if (includeExtensions.any { include -> file.path.endsWith(include) }) {
                        loc += FileLinecCounter.loc(file.path)
                    }
                }
            }
        }
        return loc
    }
}