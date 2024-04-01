@file:Suppress("UnstableApiUsage")

package core

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project

@Suppress("EnumEntryName")
enum class FlavorDimension {
    environment,
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
@Suppress("EnumEntryName")
enum class Flavors(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null
) {
    dev(FlavorDimension.environment, "", "-dev"),
    production(FlavorDimension.environment)
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: Flavors) -> Unit = {}
) {
    commonExtension.apply {

        flavorDimensions += FlavorDimension.environment.name

        productFlavors {
            Flavors.values().forEach {
                create(it.name) {
                    // main block configuration
                    dimension = it.dimension.name

                    projectDir.getProps("/config/$name.properties").forEach { prop ->
                        buildConfigField("String", "${prop.key}", "${prop.value}")
                    }

                    // custom configuration
                    flavorConfigurationBlock(this, it)

                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                            versionNameSuffix = it.versionNameSuffix
                        }
                    }
                }
            }
        }
    }
}

fun ProductFlavor.configureDevFlavor() {
    ndk {
        abiFilters.apply {
            add("arm64-v8a")
            add("armeabi-v7a")
            add("x86_64")
            add("x86")
        }
    }
}

fun ProductFlavor.configureProdFlavor() {
    ndk {
        abiFilters.apply {
            add("arm64-v8a")
            add("armeabi-v7a")
            add("x86_64")
        }
    }
}