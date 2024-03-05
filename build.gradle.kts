plugins {
    kotlin("jvm") version "1.9.21"
    id("com.apollographql.apollo3") version "3.8.2"
}

group = "com.nimeji"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-runtime:3.8.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
}

apollo {
    service("service") {
        packageName.set("com.nimeji")
    }
}