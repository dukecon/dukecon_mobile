buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven("https://dl.bintray.com/icerockdev/plugins")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")

        val kotlinVersion = "1.4.30"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))

        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.3.0")
        classpath("co.touchlab:kotlinxcodesync:0.2")
        classpath("com.github.jengelman.gradle.plugins:shadow:6.0.0")
        classpath("co.touchlab:kotlinnativecocoapods:0.11")
        classpath("dev.icerock.moko:network-generator:0.11.0")
    }
}

group = "org.dukecon.mobile"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://dl.bintray.com/ekito/koin")
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx/") }
        maven { url = uri("https://jitpack.io") }
        maven("https://dl.bintray.com/icerockdev/plugins")
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
