package app.android.buildlogic

import app.android.buildlogic.config.configureAndroidApplication
import app.android.buildlogic.config.configureAndroidProject
import app.android.buildlogic.setup.setupTrackPlugin
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class ApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(receiver = target) {
            apply(plugin = "com.android.application")
            val applicationExtension = extensions.getByType<ApplicationExtension>()
            configureAndroidApplication(applicationExtension = applicationExtension)
            configureAndroidProject(commonExtension = applicationExtension)
            setupTrackPlugin()
        }
    }

}
