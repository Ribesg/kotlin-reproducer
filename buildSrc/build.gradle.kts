// We need to define these even before Versions.kt exists in the context
val kotlinVersion = "1.4.32"
val androidGradleVersion = "4.0.2" // Should only be needed in this file

// Pass this value to Versions.kt as a system property
System.setProperty("VERSION_KOTLIN", kotlinVersion)

repositories {
    mavenCentral()
    google()
    jcenter() // https://youtrack.jetbrains.com/issue/KT-44730
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.android.tools.build:gradle:$androidGradleVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
