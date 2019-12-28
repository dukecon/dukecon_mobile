import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("project-dependencies-graph-plugin")
}

buildscript {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://maven.fabric.io/public") }
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.kotlinSerializationGradlePlugin)
        classpath("com.google.gms:google-services:4.3.3")
        classpath("io.spring.gradle:dependency-management-plugin:1.0.7.RELEASE")
        classpath("io.fabric.tools:gradle:1.31.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()

        maven { url = uri("https://kotlin.bintray.com/kotlin") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://dl.bintray.com/icerockdev/moko") }
        maven { url = uri("https://kotlin.bintray.com/ktor") }
        maven { url = uri("https://dl.bintray.com/aakira/maven") }
    }

    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.register("clean", Delete::class).configure {
    delete(rootProject.buildDir)
}