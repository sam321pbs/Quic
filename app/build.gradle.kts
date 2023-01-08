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
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Espresso.androidJunitRunner

        val NEWS_API_KEY: String = gradleLocalProperties(
            rootDir
        ).getProperty("NEWS_API_KEY")
        val WEATHER_API_KEY: String = gradleLocalProperties(
            rootDir
        ).getProperty("WEATHER_API_KEY")
        val FINANCE_API_KEY: String = gradleLocalProperties(
            rootDir
        ).getProperty("FINANCE_API_KEY")

        buildConfigField("String", "NEWS_API_KEY", NEWS_API_KEY)
        buildConfigField("String", "WEATHER_API_KEY", WEATHER_API_KEY)
        buildConfigField("String", "FINANCE_API_KEY", FINANCE_API_KEY)
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
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.fragmentKtx)

    // network
    implementation(Network.retrofit)
    implementation(Network.gsonConverter)
    implementation(Network.gson)

    // DI
    implementation(DependencyInjection.hiltAndroid)

    kapt(DependencyInjection.hiltCompiler)

    // play
    implementation(GooglePlay.playServiceLocation)

    // glide
    implementation(Glide.glide)
    annotationProcessor(Glide.glideCompiler)

    // timber
    implementation(Logging.timber)

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