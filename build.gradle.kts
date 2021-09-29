buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")

        val kotlinVersion = "1.5.30"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath("co.touchlab:kotlinxcodesync:0.2")
        classpath("co.touchlab:kotlinnativecocoapods:0.11")
        classpath("dev.icerock.moko:network-generator:0.16.0")
    }
}

group = "org.dukecon.mobile"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
