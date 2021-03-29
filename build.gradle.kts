allprojects {

    repositories {
        mavenCentral()
        google()
        jcenter()
    }

}

tasks.withType<Wrapper> {
    gradleVersion = Versions.gradle
    distributionType = Wrapper.DistributionType.ALL
}
