buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven("https://dl.bintray.com/icerockdev/plugins")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")

        val kotlinVersion = "1.4.30"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath("co.touchlab:kotlinxcodesync:0.2")
        classpath("com.github.jengelman.gradle.plugins:shadow:6.0.0")
        classpath("co.touchlab:kotlinnativecocoapods:0.11")
        classpath("dev.icerock.moko:network-generator:0.11.0")
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
        maven("https://dl.bintray.com/icerockdev/plugins")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
