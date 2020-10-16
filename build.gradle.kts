buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.0")

        val kotlinVersion = "1.4.10"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath("co.touchlab:kotlinxcodesync:0.2")
        classpath("com.github.jengelman.gradle.plugins:shadow:6.0.0")
        classpath("co.touchlab:kotlinnativecocoapods:0.11")
    }
}

group = "org.dukecon.mobile"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}


allprojects {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
