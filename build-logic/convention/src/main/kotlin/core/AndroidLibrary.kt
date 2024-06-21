package core

import com.android.build.api.dsl.LibraryExtension
import data.LibConf
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidLibrary() =
    extensions.getByType<LibraryExtension>().apply {
        namespace = LibConf.NAMESPACE
        configureDefaults()
    }