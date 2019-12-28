plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
    id("dev.icerock.mobile.multiplatform")
}

android {
    setDefaults()
}

val mppModules = listOf(
        Modules.MultiPlatform.domain,
        Modules.MultiPlatform.core
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.ktorUtils)

    mppLibrary(Deps.Libs.MultiPlatform.napier)

    mppModules.forEach { mppModule(it) }
}
