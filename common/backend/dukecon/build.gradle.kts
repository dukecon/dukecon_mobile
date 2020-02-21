plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform")
}

android {
    setDefaults()
}

val mppModules = listOf(
        Modules.MultiPlatform.data,
        Modules.MultiPlatform.core
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

dependencies {
    mppLibraries.forEach { mppLibrary(it) }
    mppModules.forEach { mppModule(it) }
}
