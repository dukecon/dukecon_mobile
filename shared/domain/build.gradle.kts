plugins { kotlin("multiplatform") }

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
        implementation("io.ktor:ktor-utils:1.5.3")
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
    val iosMain by getting
    val iosTest by getting
  }
}
