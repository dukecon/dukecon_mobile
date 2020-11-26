import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("co.touchlab.native.cocoapods")
}

group = "org.dukecon.mobile"
version = "0.0.2"

kotlin {

    ios()

    cocoapodsext {
        // Configure fields required by CocoaPods.
        summary = "DukeconSdk Javaland"
        homepage = "https://dukecon.org"
        framework {
            isStatic = false
            export(project(":shared:core"))
            export(project(":shared:domain"))
            export(project(":shared:mvvm"))
            export(project(":shared:data"))
            export(project(":shared:backend:dukecon"))
            transitiveExport = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared:core"))
                api(project(":shared:domain"))
                api(project(":shared:mvvm"))
                api(project(":shared:data"))
                api(project(":shared:backend:dukecon"))

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
