plugins {
  kotlin("multiplatform")
  id("maven-publish")
}

group = "org.dukecon.mobile"

version = "0.0.1-SNAPSHOT"

kotlin {
  jvm()
  ios()
  sourceSets {
    val commonMain by getting { dependencies { implementation("io.ktor:ktor-utils:1.5.2") } }
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
