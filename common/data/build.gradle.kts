plugins {
    kotlin("multiplatform")

    id("kotlin-android-extensions")
    id("kotlinx-serialization")
    id("co.touchlab.kotlinxcodesync")
    id("maven-publish")
}

kotlin {
    targets {
        ios()
        jvm()
        jvm("android")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":common:core"))
                api(project(":common:domain"))

                implementation(Deps.Libs.MultiPlatform.Coroutines.common)
                implementation(Deps.Libs.MultiPlatform.KtorUtils.common)
                implementation(Deps.Libs.MultiPlatform.KtorClient.common)
                implementation(Deps.Libs.MultiPlatform.KtorClientJson.common)
                implementation(Deps.Libs.MultiPlatform.KtorClientLogging.common)
                implementation(Deps.Libs.MultiPlatform.Serialization.common)
                implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.common)
            }
        }

        val jvmMain by getting {
            dependencies {

                implementation(Deps.Libs.MultiPlatform.KtorClient.jvm)
                implementation(Deps.Libs.MultiPlatform.KtorClientJson.android)
                implementation(Deps.Libs.MultiPlatform.KtorClientLogging.android)
                implementation(Deps.Libs.MultiPlatform.Serialization.android)
                implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.android)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Deps.Libs.MultiPlatform.KtorClient.android)
                implementation(Deps.Libs.MultiPlatform.KtorClientJson.android)
                implementation(Deps.Libs.MultiPlatform.KtorClientLogging.android)
                implementation(Deps.Libs.MultiPlatform.KtorClientJsonSerializer.android)
            }
        }

        val iosMain by getting {

            dependencies {
                implementation(Deps.Libs.MultiPlatform.KtorClient.ios)
            }
        }
    }
}
