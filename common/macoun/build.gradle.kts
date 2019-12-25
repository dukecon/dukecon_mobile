plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroidExtensions)
}

configurations.create("compileClasspath")

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinxCoroutinesCore)
    implementation(Libraries.kotlinxSerializeJvm)
    implementation(Libraries.ktorUtilsJvm)
    implementation(Libraries.ktorCoreJvm)
    implementation(Libraries.ktorSerializationJvm)
    implementation(Libraries.ktorLoggingJvm)
    implementation(Libraries.ktorOkhttpJvm)
    implementation("com.google.code.gson:gson:2.8.6")


    implementation("org.slf4j:slf4j-api:1.7.28")

    testImplementation(Libraries.kotlinTestJvm)
    testImplementation(Libraries.kotlinxCoroutinesCore)
    testImplementation(Libraries.kotlinTestJunit)
}

kotlin {
    targets {
        android()
        jvm()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:data"))

                implementation(Libraries.kotlinStdLibCommon)
                implementation(Libraries.kotlinxCoroutinesCommon)
                implementation(Libraries.ktorCoreCommon)
                implementation(Libraries.ktorUtilsCommon)
                implementation(Libraries.ktorSerializationCommon)
                implementation(Libraries.ktorLoggingCommon)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(Libraries.kotlinTestCommon)
                implementation(Libraries.kotlinTestAnnotations)
                implementation(Libraries.kotlinxCoroutinesCommon)

            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Libraries.kotlinStdLib)
                implementation(Libraries.kotlinxCoroutinesCore)
                implementation(Libraries.kotlinxSerializeJvm)
                implementation(Libraries.ktorUtilsJvm)
                implementation(Libraries.ktorCoreJvm)
                implementation(Libraries.ktorSerializationJvm)
                implementation(Libraries.ktorLoggingJvm)
                implementation(Libraries.ktorOkhttpJvm)
                implementation("com.google.code.gson:gson:2.8.6")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Libraries.kotlinTestJvm)
                implementation(Libraries.kotlinxCoroutinesCore)
                implementation(Libraries.kotlinTestJunit)
            }
        }
    }
}