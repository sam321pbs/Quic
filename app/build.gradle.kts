
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

        testInstrumentationRunner = Espresso.androidJunitRunner
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
    implementation(AndroidX.cardView)

    // network
    implementation(Network.retrofit)
    implementation(Network.gsonConverter)
    implementation(Network.gson)

    // DI
    implementation(DependencyInjection.hiltAndroid)
    implementation(fileTree(mapOf(
        "dir" to "/Users/samuelmengistu/AndroidStudioProjects/Quic/app/lib",
        "include" to listOf("*.aar", "*.jar")
    )))

    kapt(DependencyInjection.hiltCompiler)

    // play
    implementation(GooglePlay.playServiceLocation)

    // glide
    implementation(Glide.glide)
    annotationProcessor(Glide.glideCompiler)

    // testing
    testImplementation(Testing.mockito)
    implementation(Espresso.espressoCore)
    implementation(Espresso.testRunner)
    implementation(Espresso.testRules)
    implementation(Espresso.junitKtx)
    implementation(Espresso.fragmentTesting)
    androidTestImplementation(Espresso.hiltAndroidTesting)
    kaptAndroidTest(DependencyInjection.hiltCompiler)

}

kapt {
    correctErrorTypes = true
}