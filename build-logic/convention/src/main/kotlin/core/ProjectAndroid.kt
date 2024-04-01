@file:Suppress("UnstableApiUsage")

package core

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.Properties

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