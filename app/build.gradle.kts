import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.sammengistu.quic"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        val NEWS_API_KEY: String = gradleLocalProperties(rootDir).getProperty("NEWS_API_KEY")

        buildConfigField("String", "NEWS_API_KEY", NEWS_API_KEY)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // android
    implementation(AndroidX.androidxCore)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.liveData)
    implementation(AndroidX.legacySupport)

    // testing
    implementation(AndroidX.fragmentTesting)

    // network
    implementation(Network.retrofit)
    implementation(Network.gsonConverter)
    implementation(Network.gson)

    // DI
    implementation(DependencyInjection.hiltAndroid)
    kapt(DependencyInjection.hiltCompiler)
}

kapt {
    correctErrorTypes = true
}