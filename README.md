# Kotlin Multiplatform Playground

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.

- Android - **OK**
- iOS - **OK**
- Web - in progress
- Desktop - in progress
- Server - in progress

## Android
<img src="document/android_screenshot.png" width="300">

## iOS
<img src="document/ios_screenshot.png" width="300">

## Building the app

> [!IMPORTANT]
> 
> You need to add the **NEWS_API_KEY** in your `local.properties` for Android.
>
> For iOS, you can update the api key in the `data` module, `iosMain/source.impl/NewsRemoteDataSource.ios.kt`.
>
> To get your own api key, go to --> https://newsapi.org/

## Languages, libraries and tools used

1. Kotlin
2. Kotlin Coroutine
3. Kotlinx Serialization
4. Ktor client library
5. Compose Multiplatform
6. Koin
7. SQL delight
8. Coil

> [!WARNING]
> 
> For more details, you can check the official document:
>
> https://thesetox.github.io/KMP-Playground-Document/introduction.html **(Work in Progress)**

## Kotlin Multiplatform with Compose Multiplatform (Main Structure)

![image_diagram.png](document/image_diagram.png)

## Main Goal for Architecture (Status: Work in Progress)

![image_fun_arch.png](document/image_fun_arch.png)

## Folders

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.
  This is being used as the main `/shared` folder.

* `/domain` is a shared module where it is responsible on creating the business logic. You usually don't
need to add code to the platform-specific but you can add additional business logic depending on the target

* `/data` is a shared module where it is responsible for fetching the data. You can fetch platform-specific
data.

>[!NOTE]
>**Note:** Compose/Web is Experimental and may be changed at any time. Use it only for evaluation purposes.

## Acknowledgements

Special thanks to https://github.com/petros-efthymiou.

The project is an insipration from https://github.com/petros-efthymiou/DailyPulse
