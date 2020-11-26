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


}

dependencies {
    /*
    implementation (project(":common:data"))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.4.10")
    implementation ("com.android.support:appcompat-v7:28.0.0")
    implementation ("com.android.support.constraint:constraint-layout:1.1.3")

     */

}
