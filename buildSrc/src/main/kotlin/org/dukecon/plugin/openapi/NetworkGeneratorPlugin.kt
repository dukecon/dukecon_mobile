package org.dukecon.plugin.openapi

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.openapitools.generator.gradle.plugin.OpenApiGeneratorPlugin

class NetworkGeneratorPlugin : Plugin<Project> {
    private val openApiGenerator = OpenApiGeneratorPlugin()

    override fun apply(target: Project) {
        openApiGenerator.apply(target)

        val generatedDir = "${target.buildDir}/generate-resources/main"

        target.afterEvaluate {
            /*
            extensions.findByType(KotlinMultiplatformExtension::class.java)?.run {
                val sourceSet = sourceSets.getByName(KotlinSourceSet.COMMON_MAIN_SOURCE_SET_NAME)
                val sources = "$generatedDir/src/main/kotlin"
                sourceSet.kotlin.srcDir(sources)
            }
             */

            val removeGeneratedCodeTask =
                tasks.create("removeGeneratedOpenApiCode", Delete::class.java) {
                    delete(file(generatedDir))
                }

            tasks.findByName("openApiGenerate")?.let {
                it.dependsOn(removeGeneratedCodeTask)

                tasks.findByName("preBuild")?.dependsOn(it)
                tasks.findByName("compileKotlinIosX64")?.dependsOn(it)
                tasks.findByName("compileKotlinIosArm64")?.dependsOn(it)
            }
        }
    }
}