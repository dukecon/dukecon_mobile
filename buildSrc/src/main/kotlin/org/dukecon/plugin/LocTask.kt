package org.dukecon.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

// DefaultTask is the standard Task implementation. You can extend this to implement your own task types.
open class LocTask : DefaultTask() { // Keep the file open otherwise gradle won't be able to generate the proxy class.

    @Input
    lateinit var input: List<String>

    @OutputDirectory
    lateinit var report: String

    init {
        group = "report"
        description = "Counts lines of code"
    }

    @TaskAction
    fun run() {
        println("${project.buildDir}")
        val directories = input.forEach { dir ->
            println("directory=$dir")
            println(DirectoryLocCounter.loc(dir, listOf(".kt")))
        }
    }
}