plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
    id("co.touchlab.kotlinxcodesync")
    id("maven-publish")
}

val ideaActive = System.getProperty("idea.active") == "true"

android {
    setDefaults()
}

kotlin {
    jvm()
    android()

    val iosArm32 = iosArm32("iosArm32")
    val iosArm64 = iosArm64("iosArm64")
    val iosX64 = iosX64("iosX64")

    if (ideaActive) {
        iosX64("ios")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                 api(project(":common:core"))
                api(project(":common:domain"))

                api(kotlin("stdlib-common", Versions.kotlin))
                api(Deps.Libs.MultiPlatform.Coroutines.common)
                api(Deps.Libs.MultiPlatform.KtorUtils.common)
                api(Deps.Libs.MultiPlatform.KtorClient.common)
                api(Deps.Libs.MultiPlatform.KtorClientJson.common)
                api(Deps.Libs.MultiPlatform.KtorClientLogging.common)
                api(Deps.Libs.MultiPlatform.Serialization.common)
                api(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.common)
            }
        }

        val mobileMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib", Versions.kotlin))
                api(Deps.Libs.MultiPlatform.Coroutines.android)
                api(Deps.Libs.MultiPlatform.KtorUtils.jvm)
                api(Deps.Libs.MultiPlatform.KtorClient.android)
                api(Deps.Libs.MultiPlatform.KtorClientJson.android)
                api(Deps.Libs.MultiPlatform.KtorClientLogging.android)
                api(Deps.Libs.MultiPlatform.Serialization.android)
                api(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.android)
            }
        }

        val androidMain by getting {
            dependsOn(mobileMain)
            dependencies {
                api(kotlin("stdlib", Versions.kotlin))
                api(Deps.Libs.MultiPlatform.Coroutines.android)
                api(Deps.Libs.MultiPlatform.KtorUtils.jvm)
                api(Deps.Libs.MultiPlatform.KtorClient.android)
                api(Deps.Libs.MultiPlatform.KtorClientJson.android)
                api(Deps.Libs.MultiPlatform.KtorClientLogging.android)
                api(Deps.Libs.MultiPlatform.Serialization.android)
                api(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.android)
            }
        }

        val iosMain = if (ideaActive) {
            getByName("iosMain")
        } else {
            create("iosMain")
        }

        iosMain.apply {
            dependsOn(mobileMain)

            dependencies {
                implementation(Deps.Libs.MultiPlatform.Coroutines.ios)
                implementation(Deps.Libs.MultiPlatform.KtorUtils.ios)
                api(Deps.Libs.MultiPlatform.KtorClient.ios)
                api(Deps.Libs.MultiPlatform.KtorClientJson.ios)
                api(Deps.Libs.MultiPlatform.KtorClientLogging.ios)
                api(Deps.Libs.MultiPlatform.Serialization.ios)
                api(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.ios)
            }
        }

        val iosArm32Main by getting
        val iosArm64Main by getting
        val iosX64Main by getting

        configure(listOf(iosArm32Main, iosArm64Main, iosX64Main)) {
            dependsOn(iosMain)
        }
    }
}
