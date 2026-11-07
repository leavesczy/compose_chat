import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "app.android.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.leavesczy.track.gradle)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.app.android.application.get().pluginId
            implementationClass = "app.android.buildlogic.ApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.app.android.library.get().pluginId
            implementationClass = "app.android.buildlogic.LibraryConventionPlugin"
        }
        register("androidxCompose") {
            id = libs.plugins.app.androidx.compose.get().pluginId
            implementationClass = "app.android.buildlogic.ComposeConventionPlugin"
        }
        register("kotlinParcelize") {
            id = libs.plugins.app.kotlin.parcelize.get().pluginId
            implementationClass = "app.android.buildlogic.ParcelizeConventionPlugin"
        }
    }
}
