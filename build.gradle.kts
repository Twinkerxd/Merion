plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.30.0")
    testImplementation("com.codeborne:selenide:7.7.3")

    testImplementation("com.squareup.okhttp3:okhttp:4.12.0") //api

    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.2") //object->json->object
    testImplementation("com.squareup.okhttp3:logging-interceptor:4.12.0") //logs
}

tasks.test {
    useJUnitPlatform()
}