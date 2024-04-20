plugins {
    id("spotifycompose.android.library")
}

android {
    namespace = "com.him.sama.spotifycompose.common.test_util"
}

dependencies {
    implementation(project(":common:base"))
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)

    api(libs.junit)
    api(libs.mockk)
    api(libs.jetbrains.kotlinx.coroutine.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.kotlin.junit)
}