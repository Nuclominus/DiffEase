@file:Suppress("UnstableApiUsage")

package core

import com.android.build.api.dsl.CommonExtension
import data.AndroidAppConf
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileOptions {
            // Configure CI to use Java 19
            sourceCompatibility = JavaVersion.VERSION_19
            targetCompatibility = JavaVersion.VERSION_19
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                // Treat all Kotlin warnings as errors (disabled by default)
                // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
                val warningsAsErrors: String? by project
                allWarningsAsErrors.set(warningsAsErrors.toBoolean())

                freeCompilerArgs.addAll(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-Xjvm-default=all-compatibility",
                    "-Xcontext-receivers",
                )

                jvmTarget.set(JvmTarget.fromTarget(JavaVersion.VERSION_19.toString()))
            }
        }

        lint {
            baseline = file("lint-baseline.xml")
            abortOnError = true
        }

    }
}
