plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    setDefaults()
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