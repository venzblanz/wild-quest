plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.android.finalproject"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.android.finalproject"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // ─── ROOM DATABASE ──────────────────────────────────────────
    // room-runtime: the core Room library (Database, Dao, Entity)
    implementation("androidx.room:room-runtime:2.6.1")

    // room-ktx: adds Kotlin Coroutine support (suspend functions in DAO)
    implementation("androidx.room:room-ktx:2.6.1")

    // kapt: annotation processor — reads @Entity, @Dao, @Database and
    // generates the actual database implementation code during build
    kapt("androidx.room:room-compiler:2.6.1")

    // ─── COROUTINES ─────────────────────────────────────────────
    // Needed for lifecycleScope.launch { } in activities
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // ────────────────────────────────────────────────────────────

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}