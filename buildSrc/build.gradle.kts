plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "my"
version = "1.0"

gradlePlugin {
    plugins {
        register("LocPlugin") {
            id = "loc-plugin"
            implementationClass = "org.dukecon.plugin.LocPlugin"
        }
    }
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

repositories {
    jcenter()
}