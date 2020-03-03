plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("maven-publish")
}

val ideaActive = System.getProperty("idea.active") == "true"


kotlin {
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
                api(kotlin("stdlib-common", Versions.kotlin))
                api(Deps.Libs.MultiPlatform.Coroutines.common)
                api(Deps.Libs.MultiPlatform.KtorUtils.common)
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
