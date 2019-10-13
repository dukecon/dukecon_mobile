buildscript {
    repositories {
        google()
        jcenter()
        maven { setUrl( "https://maven.fabric.io/public") }


    }
    dependencies {
        classpath (BuildPlugins.androidGradlePlugin)
        classpath (BuildPlugins.kotlinGradlePlugin)
        classpath (BuildPlugins.kotlinSerializationGradlePlugin)
        classpath ("com.google.gms:google-services:4.3.2")
        classpath ("io.spring.gradle:dependency-management-plugin:1.0.7.RELEASE")
        classpath ("io.fabric.tools:gradle:1.29.0")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean").configure {
    delete("build")
}