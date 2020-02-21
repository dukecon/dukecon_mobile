plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
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
    mppModules.forEach { mppModule(it) }

    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.serialization)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClientLogging)

    mppLibrary(Deps.Libs.MultiPlatform.settings)
    mppLibrary(Deps.Libs.MultiPlatform.napier)

    implementation("androidx.preference:preference:1.1.0")
}
