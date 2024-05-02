plugins {
    id("spotifycompose.android.library")
    id("spotifycompose.android.hilt")
    id("com.google.devtools.ksp")
    id("spotifycompose.android.library.compose")
}

android {
    namespace = "com.him.sama.spotifycompose.common.ui"
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:base"))
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.navigation.compose)
    api(libs.androidx.compose.activity)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.window.size)
    api(libs.flowext)
    api(libs.coil.compose)

    // Android Studio Preview support
    debugApi(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)
}