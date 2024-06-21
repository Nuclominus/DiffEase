@file:Suppress("UnstableApiUsage")

package core

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import data.AndroidAppConf
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.io.FileInputStream
import java.util.Properties

internal fun Project.configureAndroidApplication() =
    extensions.getByType<ApplicationExtension>().apply {


        defaultConfig {
            targetSdk = AndroidAppConf.COMPILE_SDK

            versionName = AndroidAppConf.APP_VERSION
            versionCode = AndroidAppConf.VERSION_CODE
            namespace = AndroidAppConf.NAMESPACE

            multiDexEnabled = true
        }

        configureDefaults()

        lint {
            baseline = file("lint-baseline.xml")
            abortOnError = true
        }

//        configureSigningAndroid(this)

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }


internal fun Project.configureDefaults() =
    extensions.getByType(CommonExtension::class.java).apply {
        compileSdk = AndroidAppConf.COMPILE_SDK

        defaultConfig.apply {
            minSdk = AndroidAppConf.MIN_SDK

            buildFeatures {
                // Disable unused features to reduce build time.
                // Enable features as needed in other configurations.
                aidl = false
                buildConfig = false
                dataBinding {
                    enable = false
                }
                viewBinding = true
                prefab = false
                compose = false
            }
        }

        compileOptions {
            sourceCompatibility = AndroidAppConf.javaVersion
            targetCompatibility = AndroidAppConf.javaVersion
        }
    }


/**
 * Configure base project resources
 */
internal fun Project.configureGlobalsAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {

        defaultConfig {

            val supportedLanguages = listOf("en")
            resourceConfigurations.addAll(supportedLanguages)

            // Global config
            val global = projectDir.getProps("/config/global.properties")
            global.forEach { prop ->
                buildConfigField("String", "${prop.key}", "${prop.value}")
            }

            // Global resources
            val resources = projectDir.getProps("/config/resource.properties")
            resources.forEach { prop ->
                resValue("string", "${prop.key}", "${prop.value}")
            }
        }
    }
}

fun File.getProps(filePath: String) = Properties().apply {
    load(FileInputStream(File(parent + filePath)))
}