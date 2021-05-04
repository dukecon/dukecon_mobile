plugins {
  kotlin("multiplatform")
  id("maven-publish")
}

group = "org.dukecon.mobile"

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
        implementation("io.ktor:ktor-utils:1.5.3")
        implementation("co.touchlab:stately-common:1.1.4")
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
