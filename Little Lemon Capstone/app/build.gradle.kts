plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")
    id ("org.jetbrains.kotlin.plugin.serialization")

}

android {
    namespace = "com.ahmed.littlelemoncapstone"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ahmed.littlelemoncapstone"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    implementation(libs.androidx.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("androidx.navigation:navigation-compose:2.5.3")

    implementation ("io.ktor:ktor-client-android:2.1.3")
    implementation ("io.ktor:ktor-client-content-negotiation:2.1.3")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:2.1.3")


    implementation ("androidx.room:room-runtime:2.5.1")
    kapt ("androidx.room:room-compiler:2.5.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.github.bumptech.glide:okhttp3-integration:4.12.0")
    implementation ("com.github.bumptech.glide:recyclerview-integration:4.12.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation ("com.github.bumptech.glide:compose:1.0.0-alpha.1")

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation ("androidx.compose.runtime:runtime-livedata:1.3.2")

    implementation ("androidx.compose.foundation:foundation:1.0.0")
}