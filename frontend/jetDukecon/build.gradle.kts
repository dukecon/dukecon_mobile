plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    id("kotlinx-serialization")
    kotlin("android")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }

    }

    flavorDimensions("conferences")

    productFlavors {
        create("javaland") {
            applicationId = "org.dukecon.mobile.javaland"
            setDimension("conferences")
            dependencies {
                implementation(project(":shared:backend:dukecon"))
                implementation(project(":shared:conference:javaland"))
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {
    implementation(project(":shared:core"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:data"))
    implementation(project(":shared:mvvm"))

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")

    implementation("io.ktor:ktor-utils-jvm:1.5.2")
    implementation("io.ktor:ktor-client-okhttp:1.5.2")
    implementation("io.coil-kt:coil:1.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")


    implementation("androidx.activity:activity-ktx:1.2.2")
    implementation("androidx.core:core-ktx:1.6.0-alpha01")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0-alpha05")

    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("com.github.tony19:logback-android:2.0.0")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}
