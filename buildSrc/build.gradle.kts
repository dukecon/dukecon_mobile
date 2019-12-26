plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `maven-publish`
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

gradlePlugin {
    plugins {
        register("LocPlugin") {
            id = "loc-plugin"
            implementationClass = "org.dukecon.plugin.LocPlugin"
        }
        register("DependencyGraphGenerator") {
            id = "project-dependencies-graph-plugin"
            implementationClass = "plugins.graph.DependencyGraphGeneratorPlugin"
        }
    }
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:3.5.3")
    implementation("guru.nidi:graphviz-java:0.12.1")
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