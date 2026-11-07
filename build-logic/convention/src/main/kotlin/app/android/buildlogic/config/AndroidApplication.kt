package app.android.buildlogic.config

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.kotlin.dsl.getByType
import java.io.File

fun Project.configureAndroidApplication(applicationExtension: ApplicationExtension) {
    applicationExtension.apply {
        defaultConfig {
            applicationId = "github.leavesczy.compose_chat"
            targetSdk {
                version = release(version = androidTargetSdkVersion())
            }
            versionCode = appVersionCode()
            versionName = appVersionName()
            buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
            val buildTime = if (nonDebugPackageRequested()) {
                providers.of(AppBuildTimeValueSource::class.java) {}.get()
            } else {
                ""
            }
            buildConfigField("String", "BUILD_TIME", "\"$buildTime\"")
        }
        val archiveTime = if (nonDebugPackageRequested()) {
            "_${providers.of(ApkBuildTimeValueSource::class.java) {}.get()}"
        } else {
            ""
        }
        project.extensions.getByType<BasePluginExtension>().archivesName.set(
            "compose_chat_v${appVersionName()}_${appVersionCode()}$archiveTime"
        )
        androidResources {
            localeFilters.clear()
            localeFilters.add(element = "zh")
        }
        signingConfigs {
            create("release") {
                storeFile = File(File(rootDir, "doc"), "key.jks")
                keyAlias = "leavesczy"
                keyPassword = "123456"
                storePassword = "123456"
                enableV1Signing = true
                enableV2Signing = true
                enableV3Signing = true
                enableV4Signing = true
            }
        }
        buildTypes {
            val releaseSigning = signingConfigs.findByName("release")
            debug {
                signingConfig = releaseSigning
                isMinifyEnabled = false
                isShrinkResources = false
                isDebuggable = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            release {
                signingConfig = releaseSigning
                isMinifyEnabled = true
                isShrinkResources = true
                isDebuggable = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                ndk {
                    abiFilters.add("arm64-v8a")
                }
            }
        }
        buildFeatures {
            buildConfig = true
        }
        packaging {
            jniLibs {
                excludes.add(element = "META-INF/{AL2.0,LGPL2.1}")
            }
            resources {
                excludes += setOf(
                    "**/*.md",
                    "**/*.version",
                    "**/*.properties",
                    "**/*.kotlin_module",
                    "**/CHANGES",
                    "**/LICENSE.txt",
                    "**/{AL2.0,LGPL2.1}",
                    "**/DebugProbesKt.bin",
                    "**/app-metadata.properties",
                    "**/kotlin-tooling-metadata.json",
                    "**/version-control-info.textproto",
                    "**/androidsupportmultidexversion.txt",
                )
            }
        }
    }
}
