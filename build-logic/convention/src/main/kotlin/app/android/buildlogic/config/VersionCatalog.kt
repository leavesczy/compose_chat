package app.android.buildlogic.config

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.appVersionCode(): Int =
    libs.findVersion("app-version-code").get().requiredVersion.toInt()

fun Project.appVersionName(): String =
    libs.findVersion("app-version-name").get().requiredVersion

fun Project.androidCompileSdkVersion(): Int =
    libs.findVersion("android-compile-sdk").get().requiredVersion.toInt()

fun Project.androidCompileSdkMinorVersion(): Int =
    libs.findVersion("android-compile-sdk-minor").get().requiredVersion.toInt()

fun Project.androidTargetSdkVersion(): Int =
    libs.findVersion("android-target-sdk").get().requiredVersion.toInt()

fun Project.androidMinSdkVersion(): Int =
    libs.findVersion("android-min-sdk").get().requiredVersion.toInt()

fun Project.androidBuildToolsVersion(): String =
    libs.findVersion("android-build-tools").get().requiredVersion
