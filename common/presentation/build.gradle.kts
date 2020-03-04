plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("maven-publish")
}

android {
    setDefaults()
}

kotlin {
    android()
    jvm()

    sourceSets["commonMain"].dependencies {
        implementation(project(":common:core"))
        implementation(project(":common:domain"))

        implementation(kotlin("stdlib-common", Versions.kotlin))
        implementation(Deps.Libs.MultiPlatform.KtorClient.common)
        implementation(Deps.Libs.MultiPlatform.KtorClientJson.common)
        implementation(Deps.Libs.MultiPlatform.Coroutines.common)
        implementation(Deps.Libs.MultiPlatform.Serialization.common)
        implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.common)
    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Versions.kotlin))
        implementation(Deps.Libs.MultiPlatform.KtorClient.android)
        implementation(Deps.Libs.MultiPlatform.KtorClientJson.android)
        implementation(Deps.Libs.MultiPlatform.Coroutines.android)
        implementation(Deps.Libs.MultiPlatform.Serialization.android)
        implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.android)
    }

    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib", Versions.kotlin))
        implementation(Deps.Libs.MultiPlatform.KtorClient.android)
        implementation(Deps.Libs.MultiPlatform.KtorClientJson.android)
        implementation(Deps.Libs.MultiPlatform.Coroutines.android)
        implementation(Deps.Libs.MultiPlatform.Serialization.android)
        implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.android)
    }
}
