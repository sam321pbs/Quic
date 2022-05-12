object AndroidX {
    object Version {
        const val androidxCore = "1.7.0"
        const val appcompat = "1.4.1"
        const val material = "1.5.0"
        const val lifeCycle = "2.4.1"
        const val legacy = "1.0.0"
        const val fragment = "1.4.1"
        const val cardView = "1.0.0"
    }

    const val androidxCore = "androidx.core:core-ktx:${Version.androidxCore}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifeCycle}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacy}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Version.fragment}"
    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"
    const val cardView = "androidx.cardview:cardview:${Version.cardView}"
}

object Kotlin {
    object Version {
        const val kotlinCoroutines = "1.6.1"
    }
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}"
}

object Network {
    object Version {
        const val retrofit = "2.9.0"
        const val gson = "2.9.0"
    }

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val gson = "com.google.code.gson:gson:${Version.gson}"
}

object DependencyInjection {
    object Version {
        const val hilt = "2.40.1"
    }

    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
}

object GooglePlay {
    object Version {
        const val location = "19.0.1"
    }

    const val playServiceLocation =
        "com.google.android.gms:play-services-location:${Version.location}"
}

object Glide {
    object Version {
        const val glide = "4.13.0"
    }

    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"
}

object Testing {
    object Version {
        const val jUnit = "4.13.2"
        const val mockitoVersion = "3.+"
    }

    const val jUnit = "junit:junit:${Version.jUnit}"
    const val mockito = "org.mockito:mockito-core:${Version.mockitoVersion}"
}