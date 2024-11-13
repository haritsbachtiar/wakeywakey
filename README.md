This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

# TODO LIST
  - SplashScreen [DONE]
  - Setup Realm DB [DONE]
  - Setup Koin Injection [DONE]
  - Setup Theme [WIP]
  - AlarmListScreen [WIP]
    - Create UI [DONE]
    - Integrate With Data Flow [DONE]
    - Update Countdown Text
  - AlarmDetailScreen [WIP]
    - Create UI [DONE]
    - Create Alarm Name Dialog [DONE]
    - Implement Time Slider [DONE]
    - Handle Save Button State [DONE]
    - Handle Back Navigation [DONE]
    - Integration With Data Flow [WIP]
  - AlarmTriggerScreen
    - Setup Alarm Service Manager


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…