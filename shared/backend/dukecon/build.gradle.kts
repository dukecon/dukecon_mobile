plugins {
    kotlin("multiplatform")
    id("ktor-open-api-plugin")
    id("kotlinx-serialization")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
}

openApiGenerate {
    inputSpec.set("$rootDir/specs/conference_api.json")
    generatorName.set("kotlin-ktor-client")
    apiPackage.set("org.dukecon.remote.api")
    modelPackage.set("org.dukecon.remote.api")
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
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
            kotlin.srcDir("$buildDir/generate-resources/main/src/main/kotlin")
            dependencies {
                implementation(project(":shared:core"))
                implementation(project(":shared:domain"))
                implementation(project(":shared:data"))
                implementation("io.ktor:ktor-utils:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")

                implementation("io.ktor:ktor-client-core:1.4.1")
                implementation("io.ktor:ktor-client-json:1.4.1")
                implementation("io.ktor:ktor-client-logging:1.4.1")
                implementation("io.ktor:ktor-client-serialization:1.4.1")

            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:1.4.1")
                implementation("io.ktor:ktor-client-logging-jvm:1.4.1")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:1.4.1")
                implementation("io.ktor:ktor-client-logging-jvm:1.4.1")
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.7")
                implementation("junit:junit:4.13")
                implementation("io.mockk:mockk:1.9.3")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.4.1")
            }
        }
        val iosTest by getting
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn("openApiGenerate")
}

