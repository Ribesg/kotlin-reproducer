import org.jetbrains.kotlin.gradle.plugin.KotlinTargetPreset
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    jcenter()
}

plugins {
    kotlin("multiplatform") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}

val executablePath: String? = System.getenv("EXECUTABLE_PATH")
val sdkName: String? = System.getenv("SDK_NAME")
val targetBuildDir: String? = System.getenv("TARGET_BUILD_DIR")

val buildType = System
    .getenv("CONFIGURATION")
    ?.toUpperCase()
    ?.let { NativeBuildType.valueOf(it) }
    ?: NativeBuildType.DEBUG
val targetName = "ios"
val targetArch = if (sdkName?.startsWith("iphoneos") == true) "arm64" else "x64"

kotlin {

    targetFromPreset(
        presets.getByName<KotlinTargetPreset<KotlinNativeTarget>>("ios${targetArch.capitalize()}"),
        targetName
    ) {
        binaries.executable(listOf(buildType)) {
            baseName = "app"
        }
    }

    sourceSets.all {
        languageSettings.run {
            progressiveMode = true
            useExperimentalAnnotation("kotlin.Experimental")
        }
    }

}

dependencies {

    val coroutines = "1.3.9-native-mt-2"
    val ktor = "1.4.1"
    val serialization = "1.0.0-RC2"

    "commonMainApi"("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    "commonMainApi"("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization")

    "commonMainApi"("io.ktor:ktor-client-core:$ktor")
    "commonMainApi"("io.ktor:ktor-client-serialization:$ktor")
    "commonMainApi"("io.ktor:ktor-client-json:$ktor")

    "iosMainApi"("io.ktor:ktor-client-ios:$ktor")

    // TODO Workaround inclusion of bad version of kotlinx-coroutines (1.3.9)
    if (project.properties["android.injected.invoked.from.ide"] == "true") {
        "iosMainApi"("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    } else {
        "iosMainApi"("org.jetbrains.kotlinx:kotlinx-coroutines-core") {
            version { strictly(coroutines) }
        }
    }

}

val target = kotlin.targets[targetName] as KotlinNativeTarget
val kotlinBinary = target.binaries.getExecutable(buildType)

val xcodeTaskName = "xcode"
if (executablePath != null && sdkName != null && targetBuildDir != null) {
    tasks.create<Copy>(xcodeTaskName) {
        dependsOn(kotlinBinary.linkTask)
        group = "xcode"
        destinationDir = file(targetBuildDir)
        from(kotlinBinary.outputFile)
        rename { executablePath }
    }
} else {
    tasks.create(xcodeTaskName) {
        group = "xcode"
        doLast {
            throw IllegalStateException("Please run the '$xcodeTaskName' task from XCode")
        }
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "6.6.1"
    distributionType = Wrapper.DistributionType.ALL
}
