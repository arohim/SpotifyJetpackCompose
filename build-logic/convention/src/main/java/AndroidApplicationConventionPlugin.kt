import com.android.build.api.dsl.ApplicationExtension
import com.myrealestateinvestment.configureAndroidCompose
import com.myrealestateinvestment.configureFlavors
import com.myrealestateinvestment.configureKotlinAndroid
import com.myrealestateinvestment.configureKotlinAndroidToolchain
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            configureKotlinAndroidToolchain()
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                defaultConfig.targetSdk = 33
                configureFlavors(this)
            }
        }
    }

}
