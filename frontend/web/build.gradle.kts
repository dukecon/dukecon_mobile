plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose")
}

// Add maven repositories
repositories {
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

// Enable JS(IR) target and add dependencies
kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared:domain"))
        implementation(project(":shared:backend:dukecon"))
        implementation(project(":shared:conference:javaland"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
      }
    }
    val jsMain by getting {
      dependencies {
        implementation(compose.web.web)
        implementation(compose.runtime)
        implementation("io.ktor:ktor-client-js:1.5.4")
      }
    }
  }
}