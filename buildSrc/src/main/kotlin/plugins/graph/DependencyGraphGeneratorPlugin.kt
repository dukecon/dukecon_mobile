package plugins.graph

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

open class DependencyGraphGeneratorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register<ProjectDependencyGraphTask>("projectDependencyGraph") {
            group = "Documentation"
            description = "Generate project dependency graph as png"

            outputDirectory = "${project.buildDir}/report/"
        }
    }
}
