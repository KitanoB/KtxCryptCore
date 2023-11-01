val koin_version: String by project

plugins {
    kotlin("jvm") version "1.9.10"
}

group = "com.kitano.crypto"
version = "0.0.1"


repositories {
    mavenCentral()
}

dependencies {
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.10")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
}


kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}