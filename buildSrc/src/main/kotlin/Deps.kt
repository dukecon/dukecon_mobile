import org.gradle.internal.impldep.com.google.api.client.util.Data.mapOf

object Deps {
    object Plugins {
        const val kotlinSerialization =
                "org.jetbrains.kotlin:kotlin-serialization:${Versions.Plugins.serialization}"
        const val androidExtensions =
                "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.Plugins.androidExtensions}"
    }

    object Libs {
        object Android {
            const val material = "com.google.android.material:material:${Versions.Libs.Android.material}"

            const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.Libs.Android.recyclerView}"

            const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Libs.Android.constraintLayout}"

            const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.Libs.Android.lifecycle}"

            const val gson = "com.google.code.gson:gson:2.8.6"
        }

        object MultiPlatform {
            object KotlinStdLib {
                val common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.Libs.MultiPlatform.kotlinStdLib}"
            }

            object KtorUtils {
                val android = "io.ktor:ktor-utils-jvm:${Versions.Libs.MultiPlatform.ktorClient}"
                val jvm = "io.ktor:ktor-utils-jvm:${Versions.Libs.MultiPlatform.ktorClient}"
                val common = "io.ktor:ktor-utils:${Versions.Libs.MultiPlatform.ktorClient}"
                val ios = "io.ktor:ktor-utils-native:${Versions.Libs.MultiPlatform.ktorClient}"
            }

            object KtorClient {
                val android = "io.ktor:ktor-client-okhttp:${Versions.Libs.MultiPlatform.ktorClient}"
                val jvm = "io.ktor:ktor-client-okhttp:${Versions.Libs.MultiPlatform.ktorClient}"
                val common = "io.ktor:ktor-client-core:${Versions.Libs.MultiPlatform.ktorClient}"
                val ios = "io.ktor:ktor-client-ios:${Versions.Libs.MultiPlatform.ktorClient}"
            }

            object KtorClientJson {
                val android = "io.ktor:ktor-client-json-jvm:${Versions.Libs.MultiPlatform.ktorClient}"
                val common = "io.ktor:ktor-client-json:${Versions.Libs.MultiPlatform.ktorClient}"
                val ios = "io.ktor:ktor-client-json-native:${Versions.Libs.MultiPlatform.ktorClient}"
            }

            object KtorClientJsonSerializer {
                val android = "io.ktor:ktor-client-serialization-jvm:${Versions.Libs.MultiPlatform.ktorClient}"
                val common = "io.ktor:ktor-client-serialization:${Versions.Libs.MultiPlatform.ktorClient}"
                val ios = "io.ktor:ktor-client-serialization-native:${Versions.Libs.MultiPlatform.ktorClient}"
            }

            object KtorClientLogging {
                val android = "io.ktor:ktor-client-logging-jvm:${Versions.Libs.MultiPlatform.ktorClientLogging}"
                val common = "io.ktor:ktor-client-logging:${Versions.Libs.MultiPlatform.ktorClientLogging}"
                val ios = "io.ktor:ktor-client-logging-native:${Versions.Libs.MultiPlatform.ktorClientLogging}"
            }

            object Coroutines {
                val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.MultiPlatform.coroutines}"
                val jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Libs.MultiPlatform.coroutines}"
                val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.Libs.MultiPlatform.coroutines}"
                val ios = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.Libs.MultiPlatform.coroutines}"
            }

            object Serialization {
                val android = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.Libs.MultiPlatform.serialization}"
                val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.Libs.MultiPlatform.serialization}"
                val ios = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.Libs.MultiPlatform.serialization}"
            }

            object Settings {
                val common = "com.russhwolf:multiplatform-settings:${Versions.Libs.MultiPlatform.settings}"
                val iosX64 = "com.russhwolf:multiplatform-settings-iosx64:${Versions.Libs.MultiPlatform.settings}"
                val iosArm64 = "com.russhwolf:multiplatform-settings-iosarm64:${Versions.Libs.MultiPlatform.settings}"
            }

            const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.Libs.MultiPlatform.kotlinStdLib}"

            /*
            const val ktorUtils =  "io.ktor:ktor-utils-jvm:${Versions.Libs.MultiPlatform.ktorClient}",
                    common = "io.ktor:ktor-utils:${Versions.Libs.MultiPlatform.ktorClient}",
                    ios = "io.ktor:ktor-utils-native:${Versions.Libs.MultiPlatform.ktorClient}"
            )
            val ktorClient = MultiPlatformLibrary(
                    android = "io.ktor:ktor-client-android:${Versions.Libs.MultiPlatform.ktorClient}",
                    common = "io.ktor:ktor-client-core:${Versions.Libs.MultiPlatform.ktorClient}",
                    ios = "io.ktor:ktor-client-ios:${Versions.Libs.MultiPlatform.ktorClient}"
            )
            val ktorClientJson = MultiPlatformLibrary(
                    android = "io.ktor:ktor-client-json-jvm:${Versions.Libs.MultiPlatform.ktorClient}",
                    common = "io.ktor:ktor-client-json:${Versions.Libs.MultiPlatform.ktorClient}",
                    ios = "io.ktor:ktor-client-json-native:${Versions.Libs.MultiPlatform.ktorClient}"
            )
            val ktorClientJsonSerializer = MultiPlatformLibrary(
                    android = "io.ktor:ktor-client-serialization-jvm:${Versions.Libs.MultiPlatform.ktorClient}",
                    common = "io.ktor:ktor-client-serialization:${Versions.Libs.MultiPlatform.ktorClient}",
                    ios = "io.ktor:ktor-client-serialization-native:${Versions.Libs.MultiPlatform.ktorClient}"
            )
            val ktorClientLogging = MultiPlatformLibrary(
                    android = "io.ktor:ktor-client-logging-jvm:${Versions.Libs.MultiPlatform.ktorClientLogging}",
                    common = "io.ktor:ktor-client-logging:${Versions.Libs.MultiPlatform.ktorClientLogging}",
                    ios = "io.ktor:ktor-client-logging-native:${Versions.Libs.MultiPlatform.ktorClientLogging}"
            )
            val coroutines = MultiPlatformLibrary(
                    android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.MultiPlatform.coroutines}",
                    common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.Libs.MultiPlatform.coroutines}",
                    ios = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.Libs.MultiPlatform.coroutines}"
            )
            val serialization = MultiPlatformLibrary(
                    android = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.Libs.MultiPlatform.serialization}",
                    common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.Libs.MultiPlatform.serialization}",
                    ios = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.Libs.MultiPlatform.serialization}"
            )
            val settings = MultiPlatformLibrary(
                    common = "com.russhwolf:multiplatform-settings:${Versions.Libs.MultiPlatform.settings}",
                    iosX64 = "com.russhwolf:multiplatform-settings-iosx64:${Versions.Libs.MultiPlatform.settings}",
                    iosArm64 = "com.russhwolf:multiplatform-settings-iosarm64:${Versions.Libs.MultiPlatform.settings}"
            )
*/
        }
    }
}