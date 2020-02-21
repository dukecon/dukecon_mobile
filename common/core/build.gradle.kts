plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform")
}

android {
    setDefaults()
}

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.ktorUtils)

    mppLibrary(Deps.Libs.MultiPlatform.settings)
    mppLibrary(Deps.Libs.MultiPlatform.napier)
}
