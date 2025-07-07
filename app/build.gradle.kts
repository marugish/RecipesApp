plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.9.24"
    id("com.google.gms.google-services")

    id("com.google.devtools.ksp") version "1.9.24-1.0.20"
}

android {
    namespace = "com.example.recipesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.recipesapp"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
        viewBinding = true
    }
}

dependencies {
    //implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    //implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
    //implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.0")
    //implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.24"))

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)

    // AndroidX Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.fragment)

    // AndroidX Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.legacy.support.v4)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    //implementation("androidx.room:room-runtime:2.7.1")
    //kapt("androidx.room:room-compiler:2.7.1")
    //implementation("androidx.room:room-ktx:2.7.1")

    // AndroidX Lifecycle
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // UI layer libraries
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // KotlinX
    implementation(libs.kotlinx.coroutines)

    // Tools libraries
    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    // Gson
    implementation(libs.gson)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    // Okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    // Koin
    implementation(libs.koin)
    // Firebase BoM
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    // Firebase Auth
    implementation(libs.firebase.auth)
    // Credential Manager
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)

    // Unit tests
    testImplementation(libs.junit)
    // UI tests
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)

}