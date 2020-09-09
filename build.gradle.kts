import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("project-dependencies-graph-plugin")
}

buildscript {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://maven.fabric.io/public") }
        maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.kotlinSerializationGradlePlugin)
        classpath("io.fabric.tools:gradle:1.31.0")
        classpath("co.touchlab:kotlinxcodesync:0.2")
        classpath("com.github.jengelman.gradle.plugins:shadow:4.0.3")
    }
}

group = "com.github.dukecon"
version = "0.0.1"

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://kotlin.bintray.com/ktor") }
        maven { url = uri("https://dl.bintray.com/aakira/maven") }
    }

    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
