@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.ApplicationExtension
import core.Flavors
import core.configureDevFlavor
import core.configureFlavors
import core.configureGlobalsAndroid
import core.configureKotlinAndroid
import core.configureProdFlavor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig.apply {
                    targetSdk = 34
                    multiDexEnabled = true

                    buildFeatures {
                        aidl = false
                        buildConfig = false
                        compose = false
                        dataBinding = false
                        viewBinding = true
                        prefab = false
                    }
                }

                configureFlavors(this) { flavor ->
                    when (flavor.name) {
                        Flavors.dev.name -> configureDevFlavor()
                        Flavors.production.name -> configureProdFlavor()
                    }
                }
            }
        }
    }
}