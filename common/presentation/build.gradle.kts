plugins {
    kotlin("multiplatform")
}

//https://youtrack.jetbrains.com/issue/KT-27170
configurations.create("compileClasspath")

kotlin {
    targets {
        jvm()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:domain"))

                implementation(Libraries.kotlinStdLibCommon)
                implementation(Libraries.kotlinxCoroutinesCommon)
                implementation(Libraries.ktorUtilsCommon)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Libraries.kotlinStdLib)
                implementation(Libraries.kotlinxCoroutinesCore)
                implementation(Libraries.ktorUtilsJvm)
                implementation("org.slf4j:slf4j-api:1.7.26")
            }
        }
    }
}