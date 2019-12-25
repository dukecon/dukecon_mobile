package plugins.graph

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

@CacheableTask
open class ProjectDependencyGraphTask : DefaultTask() {

    @OutputDirectory
    lateinit var outputDirectory: String

    @TaskAction
    fun generateGraph() {
        val projectDependencyGraphGenerator = ProjectDependencyGraphGenerator(project)
        val graph = projectDependencyGraphGenerator.generateGraph()
        File(outputDirectory, "${project.name}.dot").writeText(graph.toString())
    }
}
