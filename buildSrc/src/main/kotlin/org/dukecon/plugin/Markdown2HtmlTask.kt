package org.dukecon.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class Markdown2HtmlTask : DefaultTask() {

  init {
    group = "markdown"
    description = "Markdown to html converter"
  }

  @TaskAction
  fun run() {
    /*val parser = Parser.builder().build()
    val document = parser.parse("This is *Sparta*")
    val renderer = HtmlRenderer.builder().build()
    renderer.render(document)  // "<p>This is <em>Sparta</em></p>\n"

     */
  }
}
