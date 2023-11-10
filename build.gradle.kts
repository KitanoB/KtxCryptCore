import kotlin.math.ceil

val koin_version: String by project

plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jetbrains.kotlinx.kover") version "0.7.4"
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

tasks.register("updateCoverageBadge") {
    doLast {

        val coverageFile: Provider<RegularFile> = layout.buildDirectory.file("kover/coverage.txt")

        if (!coverageFile.isPresent) {
            println("Coverage file not found!")
            return@doLast
        }

        val coverageText: String = coverageFile.get().asFile.readText()

        val matchResult = Regex("""application line coverage: (\d+\.\d+)%""").find(coverageText)

        if (matchResult == null) {
            println("No match found in coverage.txt!")
            return@doLast
        }

        val coveragePercentage = matchResult.groups[1]?.value ?: return@doLast

        val readmeFile =  file("./README.md")

        if (!readmeFile.exists()) {
            println("README.md not found!")
            return@doLast
        }

        var readmeContent = readmeFile.readText()
        val roundedCoveragePercentage = ceil(coveragePercentage.toDouble()).toInt()
        readmeContent = readmeContent.replace(Regex("\\!\\[Coverage]\\(https://img.shields.io/badge/Coverage-\\d+-green\\)"), "![Coverage](https://img.shields.io/badge/Coverage-${roundedCoveragePercentage}-green)")

        readmeFile.writeText(readmeContent)

        println("Code coverage badge updated and README.md updated!")
    }
}