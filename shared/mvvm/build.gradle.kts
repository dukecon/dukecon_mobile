plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
}

group = "org.dukecon.mobile"
version = "0.0.1-SNAPSHOT"

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
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
                implementation("io.ktor:ktor-utils:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")

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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")
                implementation("junit:junit:4.13")
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
