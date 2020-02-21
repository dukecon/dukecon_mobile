plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `maven-publish`
}

repositories {
    mavenLocal()

    jcenter()
    google()

    maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
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

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    compileOnly(gradleApi())
    implementation(kotlin("stdlib"))
    implementation("dev.icerock:mobile-multiplatform:0.4.0")
    implementation("com.android.tools.build:gradle:3.5.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
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