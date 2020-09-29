plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("maven-publish")
}

kotlin {
    targets {
        ios()
        jvm()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:core"))
                implementation(Deps.Libs.MultiPlatform.Coroutines.common)
                implementation(Deps.Libs.MultiPlatform.KtorUtils.common)
            }
        }
    }
}
