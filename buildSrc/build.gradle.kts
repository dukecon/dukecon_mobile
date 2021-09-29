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
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")

    implementation(kotlin("stdlib"))
    implementation("com.android.tools.build:gradle:4.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    implementation("guru.nidi:graphviz-java:0.12.1")
    implementation("com.squareup:kotlinpoet:1.7.2")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.4.10.2")
}
