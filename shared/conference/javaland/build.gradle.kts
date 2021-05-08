plugins {
  kotlin("multiplatform")
  id("maven-publish")
}

group = "org.dukecon.config.javaland"

version = "0.0.1-SNAPSHOT"

kotlin {
  jvm()
  ios()
  js(IR) {
    // To build distributions for and run tests on browser or Node.js use one or both of:
    browser()
    nodejs()
  }
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared:core"))
        implementation(project(":shared:data"))
        implementation(project(":shared:domain"))
        implementation(project(":shared:domain"))
        implementation(project(":shared:backend:dukecon"))

        implementation("io.ktor:ktor-utils:1.5.3")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")

        implementation("io.ktor:ktor-client-core:1.5.3")
        implementation("io.ktor:ktor-client-json:1.5.2")
        implementation("io.ktor:ktor-client-logging:1.5.2")
        implementation("io.ktor:ktor-client-serialization:1.5.2")

        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
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
        implementation("junit:junit:4.13.2")
      }
    }
    val iosMain by getting
    val iosTest by getting
  }
}
