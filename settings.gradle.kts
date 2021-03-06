include(":frontend:jetpack-app")
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
            when (requested.id.id) {
                "kotlinx-serialization" -> {
                    useModule("org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:${requested.version}")
                }
            }
        }
    }
}

rootProject.name = "dukecon-mobile"

include(":shared:core")
include(":shared:domain")
include(":shared:data")
include(":shared:presentation")
include(":shared:mvvm")
include(":shared:backend:sessionize")
include(":shared:backend:dukecon")
include (":frontend:android")
include (":shared:DukeconSdk")