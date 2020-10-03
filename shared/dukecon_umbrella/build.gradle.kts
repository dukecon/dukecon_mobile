import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
}

group = "org.dukecon.mobile"
version = "0.0.2"

kotlin {

    val sdkName: String? = System.getenv("SDK_NAME")

    val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
    if (isiOSDevice) {
        iosArm64("ios") {
            binaries.framework("DukeconSdk") {
                baseName = "DukeconSdk"
                export(project(":shared:presentation"))
            }
        }
    } else {
        iosX64("ios") {
            binaries.framework("DukeconSdk") {
                baseName = "DukeconSdk"
                export(project(":shared:presentation"))
            }
        }
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "DukeconSdk Javaland"
        homepage = "https://dukecon.org"
        frameworkName = "DukeconSdk"
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:core"))
                implementation(project(":shared:domain"))
                api(project(":shared:presentation"))
                implementation(project(":shared:data"))
                implementation(project(":shared:backend:dukecon"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9-native-mt")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")

                implementation("io.ktor:ktor-client-core:1.4.1")
                implementation("io.ktor:ktor-client-json:1.4.1")
                implementation("io.ktor:ktor-client-logging:1.4.1")
                implementation("io.ktor:ktor-client-serialization:1.4.1")
                implementation("io.ktor:ktor-utils:1.4.1")
            }
        }
        val iosMain by getting {
            dependencies {
                api(project(":shared:presentation"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}