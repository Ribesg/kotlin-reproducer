# Basic pure Kotlin iOS app project for issue reproduction

https://youtrack.jetbrains.com/issue/KTOR-1073

1. Open `xcode.xcworkspace` in XCode (I used XCode 11.7)
2. Run on a device from XCode

This project uses Ktor 1.4.1, which is currently being built & published, I compiled it locally to try.1
If it's still not available at the time you see this, clone and compile Ktor 1.4.1 yourself:

```
git clone https://github.com/ktorio/ktor.git
cd ktor
git checkout 2f47208feb5cb45252f6994c48b3e1a34070ee3b
./gradlew publishToMavenLocal
```
