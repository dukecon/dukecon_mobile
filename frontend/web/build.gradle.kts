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
      }
    }
    val jsMain by getting {
      dependencies {
        implementation(compose.web.web)
        implementation(compose.runtime)
      }
    }
  }
}