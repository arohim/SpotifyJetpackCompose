import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.him.sama.myrealestateinvestment.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "myrealestateinvestment.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidHilt") {
            id = "myrealestateinvestment.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidLibrary") {
            id = "myrealestateinvestment.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "myrealestateinvestment.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "myrealestateinvestment.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
