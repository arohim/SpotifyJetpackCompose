plugins {
    id("spotifycompose.android.feature")
}

android {
    namespace = "com.him.sama.spotifycompose.feature.home"
}

dependencies {
    implementation(libs.flowext)
    implementation(libs.coil.compose)
}