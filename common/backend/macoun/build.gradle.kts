plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("dev.icerock.mobile.multiplatform")
}

android {
    setDefaults()
}

val mppModules = listOf(
        Modules.MultiPlatform.data,
        Modules.MultiPlatform.core
)

dependencies {
    androidLibrary(Deps.Libs.Android.gson)
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.serialization)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClientJson)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClientJsonSerializer)
    mppLibrary(Deps.Libs.MultiPlatform.ktorUtils)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClientLogging)

    mppLibrary(Deps.Libs.MultiPlatform.settings)
    mppLibrary(Deps.Libs.MultiPlatform.napier)

    mppModules.forEach { mppModule(it) }
}
