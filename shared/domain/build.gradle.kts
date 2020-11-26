plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:core"))
                implementation("io.ktor:ktor-utils:1.4.1")

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
                implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")
                implementation("junit:junit:4.13")
                implementation("io.mockk:mockk:1.9.3")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}
