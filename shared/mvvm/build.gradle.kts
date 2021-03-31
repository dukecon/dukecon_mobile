import org.jetbrains.compose.compose

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("org.jetbrains.compose")

  id("com.android.library")
  id("maven-publish")
}

group = "org.dukecon.mobile"

version = "0.0.1-SNAPSHOT"

repositories { google() }

kotlin {
  jvm()
  android()

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(compose.runtime)
        api(compose.foundation)
        api(compose.material)

        implementation(project(":shared:core"))
        implementation(project(":shared:domain"))

        implementation("io.ktor:ktor-utils:1.5.2")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
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

    val androidMain by getting {
      dependencies {
        // LiveData and ViewModel
        val lifecycleVersion = "2.2.0"
        implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
      }
    }

    val jvmMain by getting
  }
}

android {
  compileSdkVersion(29)
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdkVersion(21)
    targetSdkVersion(29)
  }
}
