package org.dukecon.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class LocPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.task("loc") {
        }
    }
}