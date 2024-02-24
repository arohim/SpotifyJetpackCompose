import com.android.build.api.dsl.ApplicationExtension
import com.him.sama.configureAndroidCompose
import com.him.sama.configureFlavors
import com.him.sama.configureKotlinAndroid
import com.him.sama.configureKotlinAndroidToolchain
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
