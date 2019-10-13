package org.dukecon.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

// DefaultTask is the standard Task implementation. You can extend this to implement your own task types.
open class LocTask : DefaultTask() { // Keep the file open otherwise gradle won't be able to generate the proxy class.


    @Input
    lateinit var directory: String

    init {
        group = "report" // This will be the group name for your task.
        description = "Counts lines of code"
    }

    @TaskAction // Marks a function as the action to run when the task is executed.
    fun run() {
        println("${project.buildDir}")
        val directories = directory.split(":").forEach { dir ->
            println("directory=$dir")
            println(DirectoryLocCounter.loc(dir, listOf(".kt")))
        }
    }
}