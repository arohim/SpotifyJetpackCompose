plugins {
    id("spotifycompose.android.library")
}

android {
    namespace = "com.him.sama.spotifycompose.common.base"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retromock)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.com.jakewharton.timber)
}