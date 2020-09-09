plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("maven-publish")
}



kotlin {
    targets {
        val sdkName: String? = System.getenv("SDK_NAME")

        val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
        if (isiOSDevice) {
            iosArm64("iOS")
        } else {
            iosX64("iOS")
        }

        val isWatchOSDevice = sdkName.orEmpty().startsWith("watchos")
        if (isWatchOSDevice) {
            watchosArm64("watch")
        } else {
            watchosX86("watch")
        }

        macosX64("macOS")
       jvm()
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


        val iOSMain by getting {
            dependencies {
                implementation("io.ktor:ktor-utils:${Versions.ktor}")


              }
        }

        val watchMain by getting {
            dependencies {
                implementation("io.ktor:ktor-utils:${Versions.ktor}")
             }
        }

        val macOSMain by getting {
            dependencies {
                implementation("io.ktor:ktor-utils:${Versions.ktor}")
            }
        }
    }
}
