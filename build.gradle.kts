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

    testImplementation("com.squareup.okhttp3:okhttp:4.12.0") //api okhttp

    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.2") //mapper object->json->object
    testImplementation("com.squareup.okhttp3:logging-interceptor:4.12.0") //logs

    testImplementation("io.rest-assured:rest-assured:5.5.1") //rest-assured

    testImplementation("com.github.javafaker:javafaker:1.0.2") //faker

    testImplementation("org.projectlombok:lombok:1.18.24") //lombok

}

tasks.test {
    useJUnitPlatform()
}