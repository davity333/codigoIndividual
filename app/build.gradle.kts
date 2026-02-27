plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Activa Hilt y KSP
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.jetbrainsKotlinSerialization)
}

android {
    namespace = "com.davitydev.chat"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.davitydev.chat"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    // Coil
    implementation(libs.io.coil.kt.coil.compose)

    // ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.com.squareup.retrofit2.retrofit)            // Retrofit
    implementation(libs.com.squareup.retrofit2.converter.json)      // JSON
    implementation(libs.io.coil.kt.coil.compose)                    // Coil
    implementation(libs.androidx.navigation.compose)                // Navigation
    implementation(libs.androidx.compose.material.icons.extended)   // Icons extendend
    implementation(libs.hilt.android)                               // Implementación de Hilt
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.runtime)                    // Integración con Jetpack Compose
    ksp(libs.hilt.compiler)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation(libs.kotlinx.serialization.json)
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}