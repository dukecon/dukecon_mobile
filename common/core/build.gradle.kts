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
                implementation(Libraries.kotlinStdLibCommon)
                implementation( "org.jetbrains.kotlin:kotlin-reflect:1.3.50")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Libraries.kotlinStdLib)
            }
        }
    }
}