plugins {
    id("spotifycompose.android.library")
    id("com.google.devtools.ksp")
    id("spotifycompose.android.library.compose")
}

android {
    namespace = "com.him.sama.spotifycompose.common.ui"
}

dependencies {
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

    // Android Studio Preview support
    debugApi(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)
}