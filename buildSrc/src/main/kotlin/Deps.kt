object Deps {
    object Plugins {
        const val kotlinSerialization =
                "org.jetbrains.kotlin:kotlin-serialization:${Versions.Plugins.serialization}"
        const val androidExtensions =
                "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.Plugins.androidExtensions}"
    }

    object Libs {
        object Android {
            val kotlinStdLib = AndroidLibrary(
                    name = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Libs.Android.kotlinStdLib}"
            )
            val appCompat = AndroidLibrary(
                    name = "androidx.appcompat:appcompat:${Versions.Libs.Android.appCompat}"
            )
            val material = AndroidLibrary(
                    name = "com.google.android.material:material:${Versions.Libs.Android.material}"
            )
            val recyclerView = AndroidLibrary(
                    name = "androidx.recyclerview:recyclerview:${Versions.Libs.Android.recyclerView}"
            )
            val constraintLayout = AndroidLibrary(
                    name = "androidx.constraintlayout:constraintlayout:${Versions.Libs.Android.constraintLayout}"
            )
            val lifecycle = AndroidLibrary(
                    name = "androidx.lifecycle:lifecycle-extensions:${Versions.Libs.Android.lifecycle}"
            )
            val gson = AndroidLibrary(
                    name = "com.google.code.gson:gson:2.8.6"
            )
        }

        object MultiPlatform {
            val kotlinStdLib = MultiPlatformLibrary(
                    android = Android.kotlinStdLib.name,
                    common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.Libs.MultiPlatform.kotlinStdLib}"
            )
            val ktorUtils = MultiPlatformLibrary(
                    android = "io.ktor:ktor-utils-jvm:${Versions.Libs.MultiPlatform.ktorClient}",
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
            val napier = MultiPlatformLibrary(
                    android = "com.github.aakira:napier-android:${Versions.Libs.MultiPlatform.napier}",
                    common = "com.github.aakira:napier:${Versions.Libs.MultiPlatform.napier}",
                    iosX64 = "com.github.aakira:napier-iosX64:${Versions.Libs.MultiPlatform.napier}",
                    iosArm64 = "com.github.aakira:napier-iosArm64:${Versions.Libs.MultiPlatform.napier}"
            )

            object Moko {
                val mokoNetwork = MultiPlatformLibrary(
                        common = "dev.icerock.moko:network:${Versions.Libs.MultiPlatform.mokoNetwork}",
                        iosX64 = "dev.icerock.moko:network-iosx64:${Versions.Libs.MultiPlatform.mokoNetwork}",
                        iosArm64 = "dev.icerock.moko:network-iosarm64:${Versions.Libs.MultiPlatform.mokoNetwork}"
                )
            }
        }
    }

    val plugins: Map<String, String> = mapOf(
            "kotlin-android-extensions" to Plugins.androidExtensions,
            "kotlinx-serialization" to Plugins.kotlinSerialization
    )
}