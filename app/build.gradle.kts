plugins {
    id("spotifycompose.android.application")
    id("org.jetbrains.kotlin.android")
    id("spotifycompose.android.hilt")
}

android {
    namespace = "com.him.sama.spotifycompose"

    defaultConfig {
        applicationId = "com.him.sama.spotifycompose.app"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
}

dependencies {
    implementation(project(":common:base"))
    implementation(project(":common:core"))
    implementation(project(":common:ui"))

    implementation(project(":feature:home"))
    implementation(project(":feature:search"))
    implementation(project(":feature:yourlibrary"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}