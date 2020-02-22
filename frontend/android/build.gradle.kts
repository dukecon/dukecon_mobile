import com.android.build.gradle.api.ApplicationVariant
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("io.fabric")
}

//apply from: "$rootDir/git.gradle"

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            signingConfigs {
                getByName("debug") {
                    keyAlias = "androiddebugkey"
                    keyPassword = "android"
                    storeFile = file("debug.keystore")
                    storePassword = "android"
                }
            }
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/NOTICE")
        exclude("META-INF/LICENSE")
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/common.kotlin_module")
        exclude("META-INF/*.kotlin_module")
    }

    productFlavors {
        create("bedcon") {
            flavorDimensions("sessionize")
            applicationIdSuffix = ".bedcon"
            dependencies {
                implementation(project(":common:backend:sessionize"))
            }
        }
        create("javaland") {
            flavorDimensions("sessionize")
            applicationIdSuffix = ".javaland"
            dependencies {
                implementation(project(":common:backend:dukecon"))
            }
        }
        create("macoun") {
            flavorDimensions("sessionize")
            applicationIdSuffix = ".macoun"
            dependencies {
                implementation(project(":common:backend:macoun"))
            }
        }
    }
    applicationVariants.all(object : Action<ApplicationVariant> {
        override fun execute(variant: ApplicationVariant) {
            variant.resValue("string", "versionInfo", variant.versionName)
        }
    })


}
/*
    def buildTime = new Date().format("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC"))
    def gitSha = gitHash()
    def shortVersionFromGitTag = shortVersionFromGitTag()
    def fullVersionFromGitTag = fullVersionFromGitTag()
    def numericVersionFromGitTag = numericVersionFromGitTag()

    versionName = shortVersionFromGitTag
    versionCode = numericVersionFromGitTag

    buildConfigField "String", "GIT_SHA", "\"${gitSha}\""
    buildConfigField "String", "BUILD_TIME", "\"${buildTime}\""
    buildConfigField "String", "FULL_VERSION", "\"${fullVersionFromGitTag}\""
    buildConfigField 'String', 'REVISION', "\"${gitVersionNumber()}\""

    println "Full Version: " + fullVersionFromGitTag
    println "Short Version: " + shortVersionFromGitTag
    println "Version code Version: " + versionCode

     */

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:domain"))
    implementation(project(":common:data"))
    implementation(project(":common:presentation"))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinxCoroutinesCore)
    implementation(Libraries.kotlinxSerializeJvm)
    implementation(Libraries.ktorUtilsJvm)
    implementation(Libraries.ktorCoreJvm)
    implementation(Libraries.ktorSerializationJvm)
    implementation(Libraries.ktorLoggingJvm)
    implementation(Libraries.ktorOkhttpJvm)
    implementation(Libraries.okhttInterceptorpJvm)
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.50")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.preference:preference:1.1.0")
    implementation("io.coil-kt:coil:0.6.1")
    implementation("androidx.browser:browser:1.0.0")
    implementation("androidx.multidex:multidex:2.0.1")

    implementation("org.slf4j:slf4j-api:1.7.28")
    implementation("com.github.tony19:logback-android:1.3.0-3")


    testImplementation(Libraries.kotlinTestJvm)
    testImplementation(Libraries.kotlinxCoroutinesCore)
    testImplementation(Libraries.kotlinTestJunit)
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
}

apply(mapOf("plugin" to "com.google.gms.google-services"))


