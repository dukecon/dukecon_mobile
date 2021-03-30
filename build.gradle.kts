buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven ("https://dl.bintray.com/icerockdev/plugins")
    }
    dependencies {
        val kotlinVersion = "1.4.32"

        // __LATEST_COMPOSE_RELEASE_VERSION__
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.3.2")
        classpath("com.android.tools.build:gradle:4.1.1")

        // __KOTLIN_COMPOSE_VERSION__
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))

        classpath("dev.icerock.moko:network-generator:0.11.0")
    }
}

group = "org.dukecon.mobile"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven ("https://dl.bintray.com/icerockdev/plugins")
        maven ("https://dl.bintray.com/ekito/koin")
    }
}
