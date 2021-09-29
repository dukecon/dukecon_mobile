plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("maven-publish")
}

group = "org.dukecon.mobile"
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
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
                implementation("io.ktor:ktor-client-logging-jvm:1.5.0")
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")
                implementation("junit:junit:4.13.2")
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
