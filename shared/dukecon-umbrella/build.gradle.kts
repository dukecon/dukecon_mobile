import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
}

group = "org.dukecon.mobile"
version = "0.0.2"

kotlin {
    ios()
    jvm()
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
                //implementation(project(":common:presentation"))
                implementation(project(":shared:data"))
                implementation(project(":shared:backend:dukecon"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9-native-mt")
                implementation("io.ktor:ktor-utils:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")

                implementation("io.ktor:ktor-client-core:1.4.1")
                implementation("io.ktor:ktor-client-json:1.4.1")
                implementation("io.ktor:ktor-client-logging:1.4.1")
                implementation("io.ktor:ktor-client-serialization:1.4.1")
                implementation("io.ktor:ktor-utils:1.4.1")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val iosTest by getting

    }

}