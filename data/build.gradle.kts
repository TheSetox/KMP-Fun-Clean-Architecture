import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelight)
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm("desktop")

    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(projects.domain)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.koin.core)
            implementation(libs.sql.coroutines.extensions)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sql.android.driver)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sql.native.driver)
            implementation(libs.stately.common)
        }
        desktopMain.dependencies {
            implementation(libs.sql.jvm.driver)
        }
    }
}

android {
    namespace = "org.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    val key: String = gradleLocalProperties(rootDir).getProperty("NEWS_API_KEY")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "key", key)
        }
    }
}

sqldelight {
    databases {
        create(name = "SampleDataBase") {
            packageName.set("org.example.project.db")
        }
    }
}