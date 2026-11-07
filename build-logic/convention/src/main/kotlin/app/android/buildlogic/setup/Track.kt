package app.android.buildlogic.setup

import github.leavesczy.track.composeclick.ComposeClickTrackPluginParameter
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

fun Project.setupTrackPlugin() {
    apply(plugin = "io.github.leavesczy.track")
    extensions.configure<ComposeClickTrackPluginParameter> {
        clickWrapperClass = "github.leavesczy.compose_chat.extensions.ComposeOnClick"
        skipOnClickLabel = "notCheck"
    }
}
