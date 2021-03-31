package plugins.graph

import guru.nidi.graphviz.model.MutableGraph
import guru.nidi.graphviz.model.MutableNode
import org.gradle.api.Project

class DependencyGraphGeneratorExtension {
  /**
   * ProjectGenerator allows you to filter and tweak between projects dependencies.
   * @since 0.6.0
   */
  data class ProjectGenerator
  @JvmOverloads
  constructor(
      /**
       * The name of this type of generator that should be in lowerCamelCase. The task name as well
       * as the output files will use this name.
       */
      var name: String = "",
      /** Allows to change the node for the given project. */
      var projectNode: (MutableNode, Project) -> MutableNode = { node, _ -> node },
      /** Return true when you want to include this project, false otherwise. */
      var includeProject: (Project) -> Boolean = { true },
      /** Return the output formats you'd like to be generated. */
      // var outputFormats: List<Format> = listOf(PNG, SVG),
      /** Allows you to mutate the graph and add things as needed. */
      var graph: (MutableGraph) -> MutableGraph = { it }
  ) {
    /** Gradle task name that is associated with this generator. */
    val gradleTaskName = "generateProjectDependencyGraph${name.capitalize()}"
    internal val outputFileName =
        "project-dependency-graph${name.toHyphenCase().nonEmptyPrepend("-")}"
    internal val outputFileNameDot = "$outputFileName.dot"

    companion object {
      /** Default behavior which will include everything as is. */
      @JvmStatic val ALL = ProjectGenerator()
    }
  }
}
