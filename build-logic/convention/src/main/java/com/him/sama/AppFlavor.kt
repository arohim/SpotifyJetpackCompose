package com.myrealestateinvestment

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureFlavors(commonExtension: CommonExtension<*, *, *, *>) {
    commonExtension.apply {
        val envDimension = "env"
        flavorDimensions += envDimension
        productFlavors {
            create("develop") {
                dimension = envDimension
                if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                    this.applicationIdSuffix = ".dev"
                    resValue("string", "app_name", "Dev My Real Estate Investment")
                }
            }
            create("production") {
                dimension = envDimension
                resValue("string", "app_name", "My Real Estate Investment")
            }
        }
    }
}
