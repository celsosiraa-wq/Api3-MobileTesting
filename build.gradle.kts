plugins {
    kotlin("jvm") version "2.0.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20240303")
}

application {
    mainClass.set("MainKt")
}
