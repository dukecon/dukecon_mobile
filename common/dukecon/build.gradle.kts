plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("dev.icerock.mobile.multiplatform")
    id("maven-publish")
}

android {
    setDefaults()
}

val mppModules = listOf(
        Modules.MultiPlatform.core,
        Modules.MultiPlatform.domain,
        Modules.MultiPlatform.data,
        Modules.MultiPlatform.presentation,
        Modules.MultiPlatform.RemoteBackends.dukecon
)

val mppLibraries = listOf(
        Deps.Libs.MultiPlatform.kotlinStdLib,
        Deps.Libs.MultiPlatform.coroutines,
        Deps.Libs.MultiPlatform.serialization,
        Deps.Libs.MultiPlatform.ktorClient,
        Deps.Libs.MultiPlatform.ktorClientJson,
        Deps.Libs.MultiPlatform.ktorClientJsonSerializer,
        Deps.Libs.MultiPlatform.ktorUtils,
        Deps.Libs.MultiPlatform.ktorClientLogging,
        Deps.Libs.MultiPlatform.settings
)

setupFramework(
        exports = mppModules + mppLibraries
)

dependencies {
    mppLibraries.forEach { mppLibrary(it) }
    mppModules.forEach { mppModule(it) }
}

group = "org.dukecon.mobile"
version = "0.0.1"

publishing {
    publications.withType<MavenPublication>().all {
        pom {
            description.set("Dukecon Mobile SDK")
            name.set("dukecon mobile sdk")
            url.set("https://github.com/dukecon/dukecon_mobile")
        }
    }   
}