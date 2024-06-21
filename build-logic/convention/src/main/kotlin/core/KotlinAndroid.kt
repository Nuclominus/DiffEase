@file:Suppress("UnstableApiUsage")

package core

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension


/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlin() {
    configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors.toBoolean())

            freeCompilerArgs.set(
                freeCompilerArgs.get() + listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-Xjvm-default=all-compatibility",
                    "-Xcontext-receivers",
                )
            )

            jvmTarget.set(JvmTarget.JVM_19)
        }
    }
}