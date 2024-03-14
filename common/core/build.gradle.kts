plugins {
    id("spotifycompose.android.library")
    id("spotifycompose.android.hilt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.him.sama.spotifycompose.common.core"
}

dependencies {
    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.retrofit2)
    api(libs.retrofit2.log)
    api(libs.gson.converter)
    api(libs.com.jakewharton.timber)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}