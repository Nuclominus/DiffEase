// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.ksp) apply false
    `maven-publish`
}

buildscript {
    extra.apply {
        set("libVersion", "1.2.1")
        set("sampleVersion", "1.0.1")
        set("minSdkVersion", 19)
        set("maxSdkVersion", 34)
        set("buildTools", "34.0.0")
    }

    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.hilt.gradle.plugin)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}