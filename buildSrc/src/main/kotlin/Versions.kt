@file:Suppress("MemberVisibilityCanBePrivate", "MayBeConstant", "unused")

import org.gradle.api.JavaVersion

object Versions {

    val java = JavaVersion.VERSION_1_8
    val kotlin: String = System.getProperty("VERSION_KOTLIN")
    val gradle = "6.8.3"
    val androidSdk = 30
    val androidGradleVersion = "4.0.1"

}
