import Libraries.Versions.kotlinxCoroutinesVersion
import Libraries.Versions.kotlinxSerialization

const val kotlinVersion = "1.3.61"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.5.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val kotlinSerializationGradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinMultiplatform = "kotlin-multiplatform"
    const val kotlinCocoapods = "org.jetbrains.kotlin.native.cocoapods"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinApt = "kotlin-kapt"
    const val fabric = "io.fabric"


}

object AndroidSdk {
    const val min = 19
    const val compile = 29
    const val target = compile
    const val buildTool = "29.0.2"
}

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0-rc01"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.1.0-rc02"
        const val ktor = "1.2.4"
        const val kotlinxSerialization = "0.12.0"
        const val kotlinxCoroutinesVersion = "1.3.0"


    }

    const val kotlinStdLibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinTestCommon = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"
    const val kotlinTestJvm = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
    const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"

    const val kotlinTestAnnotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinVersion"
    const val kotlinTestAnnotationsJvm = "org.jetbrains.kotlin:kotlin-test-annotations:$kotlinVersion"



/*
    'test'      : [
    'common'     : "org.jetbrains.kotlin::${versions.kotlin}",
    'annotations': "org.jetbrains.kotlin:kotlin-test-annotations-common:${versions.kotlin}",
    'jvm'        : "org.jetbrains.kotlin:kotlin-test:${versions.kotlin}",
    'junit'      : "org.jetbrains.kotlin:kotlin-test-junit:${versions.kotlin}",
    'reflect'    : "org.jetbrains.kotlin:kotlin-reflect:${versions.kotlin}",
    ],

 */


    const val kotlinxCoroutinesCommon = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$kotlinxCoroutinesVersion"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion"

    const val kotlinxSerializeCommon = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinxSerialization"
    const val kotlinxSerializeJvm = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinxSerialization"

    const val ktorUtilsCommon = "io.ktor:ktor-utils:${Versions.ktor}"
    const val ktorCoreCommon = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorSerializationCommon = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val ktorLoggingCommon = "io.ktor:ktor-client-logging:${Versions.ktor}"

    const val ktorCoreJvm = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
    const val ktorSerializationJvm = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    const val ktorLoggingJvm = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
    const val ktorOkhttpJvm = "io.ktor:ktor-client-okhttp:${Versions.ktor}"

    const val okhttInterceptorpJvm = "com.squareup.okhttp3:logging-interceptor:4.2.1"

    const val ktorUtilsJvm = "io.ktor:ktor-utils-jvm:${Versions.ktor}"

    const val ktorClientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"


    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"

}

object TestLibraries {
    private object Versions {
        const val testExtJUnit = "1.1.1"
        const val espresso = "3.2.0"
    }

    const val androidxTestExtJUnit = "androidx.test.ext:junit-ktx:${Versions.testExtJUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val kotlinTestCommon = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"
    const val kotlinTestJvm = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
    const val kotlinTestAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinVersion"
    const val kotlinTestJUnit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
}