plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
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
    compileSdk = 29
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 29
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
                implementation("io.ktor:ktor-utils:1.6.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

                implementation("io.ktor:ktor-client-core:1.5.0")
                implementation("io.ktor:ktor-client-json:1.5.0")
                implementation("io.ktor:ktor-client-logging:1.5.0")
                implementation("io.ktor:ktor-client-serialization:1.5.0")

            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:1.5.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:1.4.1")
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
                implementation("io.ktor:ktor-client-ios:1.5.0")
            }
        }
        val iosTest by getting
    }
}

