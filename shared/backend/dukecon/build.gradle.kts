plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-network-generator")
    id("maven-publish")
}

mokoNetwork {
    spec("conference") {
        inputSpec = file("$rootDir/specs/conference_api.json")
        packageName = "news"
        isInternal = false
        isOpen = true
        packageName = "org.dukecon.remote"
        //modelPackage.set("org.dukecon.remote.api")
        configureTask {
            // here can be configuration of https://github.com/OpenAPITools/openapi-generator GenerateTask
        }
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

group = "org.dukecon.mobile"
version = "0.0.1-SNAPSHOT"

@Suppress("UNUSED_VARIABLE")
kotlin {
    jvm()
    ios()
    android()
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("$buildDir/generate-resources/main/src/commonMain/kotlin")
            dependencies {
                implementation(project(":shared:core"))
                implementation(project(":shared:domain"))
                implementation(project(":shared:data"))
                implementation("io.ktor:ktor-utils:1.4.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")

                implementation("io.ktor:ktor-client-core:1.4.3")
                implementation("io.ktor:ktor-client-json:1.4.3")
                implementation("io.ktor:ktor-client-logging:1.4.3")
                implementation("io.ktor:ktor-client-serialization:1.4.3")

            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:1.4.3")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:1.4.3")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1")
                implementation("junit:junit:4.13.2")
                implementation("io.mockk:mockk:1.9.3")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.4.3")
            }
        }
        val iosTest by getting
    }
}

