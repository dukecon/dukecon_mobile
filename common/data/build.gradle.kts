import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id ("com.android.library")
    id (BuildPlugins.kotlinAndroidExtensions)
}

//https://youtrack.jetbrains.com/issue/KT-27170
configurations.create("compileClasspath")

android {
    setDefaults()
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinxCoroutinesCore)
    implementation(Libraries.kotlinxSerializeJvm)
    implementation(Libraries.ktorUtilsJvm)
}

kotlin {
    targets {
        android()
        jvm()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:domain"))

                implementation(Libraries.kotlinStdLibCommon)
                implementation(Libraries.kotlinxCoroutinesCommon)
                implementation(Libraries.kotlinxSerializeCommon)
                implementation(Libraries.ktorUtilsCommon)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Libraries.kotlinStdLib)
                implementation(Libraries.kotlinxCoroutinesCore)
                implementation(Libraries.kotlinxSerializeJvm)
                implementation(Libraries.ktorUtilsJvm)
                implementation("org.slf4j:slf4j-api:1.7.28")
            }
        }
    }
}