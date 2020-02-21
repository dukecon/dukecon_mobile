plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform")
    id("ktor-open-api-plugin")
}

android {
    setDefaults()
}

tasks {
    named<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("openApiGenerate") {
        generatorName.set("kotlin-ktor-client")
        inputSpec.set("$rootDir/specs/conference_api.json")
        /*
        inputSpec = "$rootDir/petstore-v3.0.yaml".toString()
        outputDir = "$buildDir/kotlin".toString()
        apiPackage = "org.openapitools.example.api"
        invokerPackage = "org.openapitools.example.invoker"
        modelPackage = "org.openapitools.example.model"
        configOptions = [
            dateLibrary: "java8"
        ]
        systemProperties = [
            modelDocs: "false"
        ]

         */
    }
}


val mppModules = listOf(
        Modules.MultiPlatform.data,
        Modules.MultiPlatform.core
)

val mppLibraries = listOf(
        Deps.Libs.MultiPlatform.kotlinStdLib,
        Deps.Libs.MultiPlatform.coroutines,
        Deps.Libs.MultiPlatform.serialization,
        Deps.Libs.MultiPlatform.ktorClient,
        Deps.Libs.MultiPlatform.ktorClientJson,
        Deps.Libs.MultiPlatform.ktorClientJsonSerializer,
        Deps.Libs.MultiPlatform.ktorUtils,
        Deps.Libs.MultiPlatform.ktorClientLogging,
        Deps.Libs.MultiPlatform.settings
)

dependencies {
    mppLibraries.forEach { mppLibrary(it) }
    mppModules.forEach { mppModule(it) }
}
