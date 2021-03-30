plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("dev.icerock.mobile.multiplatform-network-generator")
    id("maven-publish")
}

mokoNetwork {
    spec("conference") {
        inputSpec = file("$projectDir/specs/conference_api.json")
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

group = "org.dukecon.remote.macoun"
version = "0.0.1-SNAPSHOT"

@Suppress("UNUSED_VARIABLE")
kotlin {
    jvm()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:core"))
                implementation(project(":shared:domain"))
                implementation(project(":shared:data"))
                implementation("io.ktor:ktor-utils:1.5.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")

                implementation("io.ktor:ktor-client-core:1.5.2")
                implementation("io.ktor:ktor-client-json:1.5.2")
                implementation("io.ktor:ktor-client-logging:1.5.2")
                implementation("io.ktor:ktor-client-serialization:1.5.2")

            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:1.5.2")
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
                implementation("io.mockk:mockk:1.11.0")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.5.2")
            }
        }
        val iosTest by getting
    }
}

