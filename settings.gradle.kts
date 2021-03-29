@file:Suppress("UnstableApiUsage")

rootProject.name = "kotlin-reproducer"

val projectsDirectories = findProjects(rootProject.projectDir.resolve("modules"))

include(
    *projectsDirectories
        .map {
            it
                .relativeTo(rootProject.projectDir)
                .invariantSeparatorsPath
                .replace('/', ':')
        }
        .toTypedArray()
)

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.library") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

enableFeaturePreview("GRADLE_METADATA")

/**
 * Find projects under the provided directory.
 *
 * Any directory containing a directory named 'src' is returned as a project.
 */
fun findProjects(root: File): List<File> =
    if (root.resolve("src").isDirectory) {
        listOf(root)
    } else {
        root.listFiles { dir, _ -> dir.isDirectory }?.flatMap(::findProjects) ?: emptyList()
    }
