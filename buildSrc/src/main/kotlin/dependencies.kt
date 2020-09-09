import Libraries.Versions.kotlinxCoroutinesVersion
import Libraries.Versions.kotlinxSerialization

const val kotlinVersion = "1.4.0"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.0.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val kotlinSerializationGradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
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
        const val ktor = "1.3.0-rc"
        const val kotlinxSerialization = "0.14.0"
        const val kotlinxCoroutinesVersion = "1.3.3"
    }

    const val kotlinStdLibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinTestCommon = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"
    const val kotlinTestJvm = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
    const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"

    const val kotlinTestAnnotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinVersion"

    const val kotlinxCoroutinesCommon = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$kotlinxCoroutinesVersion"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion"
    const val kotlinxCoroutinesNative = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$kotlinxCoroutinesVersion"

    const val kotlinxSerializeCommon = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinxSerialization"
    const val kotlinxSerializeJvm = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinxSerialization"
    const val kotlinxSerializeNative = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$kotlinxSerialization"


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
    const val ktorUtilsNative = "io.ktor:ktor-utils-native:${Versions.ktor}"
}