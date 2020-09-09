plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `maven-publish`
}

repositories {
    jcenter()
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("LocPlugin") {
            id = "loc-plugin"
            implementationClass = "org.dukecon.plugin.LocPlugin"
        }
        register("PoetGeneratorPlugin") {
            id = "poet-generator-plugin"
            implementationClass = "org.dukecon.plugin.openapi.PoetGeneratorPlugin"
        }

        register("DependencyGraphGenerator") {
            id = "project-dependencies-graph-plugin"
            implementationClass = "plugins.graph.DependencyGraphGeneratorPlugin"
        }

        register("KtorOpenApiGenerator") {
            id = "ktor-open-api-plugin"
            implementationClass = "org.dukecon.plugin.openapi.NetworkGeneratorPlugin"
        }
    }
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    compileOnly(gradleApi())
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")

    implementation("guru.nidi:graphviz-java:0.12.1")
    implementation("org.openapitools:openapi-generator-gradle-plugin:5.0.0-beta2")
    implementation("com.squareup:kotlinpoet:1.5.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:0.10.0")
}

configurations.all {
    val isKotlinCompiler = name == "embeddedKotlin" ||
            name.startsWith("kotlin") ||
            name.startsWith("kapt")
    if (!isKotlinCompiler) {
        resolutionStrategy.eachDependency {
            @Suppress("UnstableApiUsage")
            if (requested.group == "org.jetbrains.kotlin" &&
                    requested.module.name == "kotlin-compiler-embeddable"
            ) useVersion("1.3.61")
        }
    }
}