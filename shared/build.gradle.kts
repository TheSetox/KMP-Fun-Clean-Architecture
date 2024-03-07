plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
        commonMain.dependencies {
            implementation(projects.domain)
            implementation(projects.data)
        }
        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
        }
    }
}

android {
    namespace = "org.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

