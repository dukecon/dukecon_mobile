plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("dev.icerock.mobile.multiplatform")
}

version = "1.0"

android {
    setDefaults()
}

kotlin {
    cocoapods {
        summary = "Common library for the Dukecon project"
        homepage = "https://github.com/dukecon/dukecon_mobile"
    }
}

val mppLibs = listOf(
        Deps.Libs.MultiPlatform.kotlinStdLib,
        Deps.Libs.MultiPlatform.coroutines,
        Deps.Libs.MultiPlatform.serialization,
        Deps.Libs.MultiPlatform.ktorUtils
)
val mppModules = listOf(
        Modules.MultiPlatform.domain,
        Modules.MultiPlatform.core,
        Modules.MultiPlatform.data,
        Modules.MultiPlatform.presentation,
        Modules.MultiPlatform.RemoteBackends.dukecon
)

setupFramework(
        exports = mppLibs + mppModules
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)

    androidLibrary(Deps.Libs.Android.lifecycle)

    mppLibs.forEach { mppLibrary(it) }
    mppModules.forEach { mppModule(it) }
}