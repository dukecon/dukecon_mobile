plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")

    id("com.android.library")
    id("maven-publish")
}

group = "org.dukecon.mobile"
version = "0.0.1-SNAPSHOT"

android {
    compileSdk = 29
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    jvm()
    ios()
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:core"))
                implementation(project(":shared:domain"))
                implementation("io.ktor:ktor-utils:1.6.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

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

        val androidMain by getting {
            dependencies {
                // LiveData and ViewModel
                val lifecycleVersion = "2.2.0"
                implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
            }
        }

        val iosMain by getting

        val iosTest by getting

        val jvmMain by getting
    }
}
