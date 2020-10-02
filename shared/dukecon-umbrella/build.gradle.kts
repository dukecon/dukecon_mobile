import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
    id("co.touchlab.kotlinxcodesync")
}

val ideaActive = System.getProperty("idea.active") == "true"

android {
    setDefaults()
}

kotlin {
    android()
    jvm()

    val iosArm32 = iosArm32("iosArm32")
    val iosArm64 = iosArm64("iosArm64")
    val iosX64 = iosX64("iosX64")

    if (ideaActive) {
        iosX64("ios")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:domain"))
                //implementation(project(":common:presentation"))
                implementation(project(":common:data"))
                implementation(project(":common:backend:dukecon"))

                implementation(kotlin("stdlib-common", Versions.kotlin))
                implementation(Deps.Libs.MultiPlatform.KtorClient.common)
                implementation(Deps.Libs.MultiPlatform.KtorClientJson.common)
                implementation(Deps.Libs.MultiPlatform.KtorClientLogging.common)
                implementation(Deps.Libs.MultiPlatform.Coroutines.common)
                implementation(Deps.Libs.MultiPlatform.Serialization.common)
                implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.common)
            }
        }

        val mobileMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib", Versions.kotlin))
                api(Deps.Libs.MultiPlatform.KtorClient.android)
                api(Deps.Libs.MultiPlatform.KtorClientJson.android)
                api(Deps.Libs.MultiPlatform.KtorClientLogging.android)
                api(Deps.Libs.MultiPlatform.Coroutines.android)
                api(Deps.Libs.MultiPlatform.Serialization.android)
                api(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.android)

            }
        }

        val androidMain by getting {
            dependsOn(mobileMain)
            dependsOn(jvmMain)
        }

        val iosMain = if (ideaActive) {
            getByName("iosMain")
        } else {
            create("iosMain")
        }

        iosMain.apply {
            dependsOn(mobileMain)

            dependencies {
                implementation(Deps.Libs.MultiPlatform.KtorClient.ios)
                implementation(Deps.Libs.MultiPlatform.KtorClientJson.ios)
                implementation(Deps.Libs.MultiPlatform.KtorClientLogging.ios)
                implementation(Deps.Libs.MultiPlatform.Coroutines.ios)
                implementation(Deps.Libs.MultiPlatform.Serialization.ios)
                implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.ios)
            }
        }

        val iosArm32Main by getting
        val iosArm64Main by getting
        val iosX64Main by getting

        version = "0.0.1"

        configure(listOf(iosArm32Main, iosArm64Main, iosX64Main)) {
            dependsOn(iosMain)
        }
        cocoapods {
            summary = "Common library for the KaMP starter kit"
            homepage = "https://github.com/touchlab/KaMPStarter"
        }

        xcodeSync {
            projectPath = "../../frontend/iosApp/iosApp.xcodeproj"
            target = "iosApp"
        }
    }

    val frameworkName = "DukeconSdk"

    configure(listOf(iosArm32, iosArm64, iosX64)) {
        compilations {
            val main by getting {
                extraOpts("-Xobjc-generics")
            }
        }
    }

    tasks.register<FatFrameworkTask>("debugFatFramework") {
        baseName = frameworkName
        group = "Universal framework"
        description = "Builds a universal (fat) debug framework"

        from(iosX64.binaries.getFramework("DEBUG"))
    }

    tasks.register<FatFrameworkTask>("releaseFatFramework") {
        baseName = frameworkName
        group = "Universal framework"
        description = "Builds a universal (release) debug framework"

        from(iosArm64.binaries.getFramework("RELEASE"), iosArm32.binaries.getFramework("RELEASE"))
    }
}
