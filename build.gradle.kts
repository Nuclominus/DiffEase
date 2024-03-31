// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.ksp) apply false
    `maven-publish`
}

buildscript {
    extra["libVersion"] = "1.2.0"
    extra["sampleVersion"] = "1.0.0"
    extra["minSdkVersion"] = 19
    extra["maxSdkVersion"] = 34
    extra["buildTools"] = "34.0.0"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.hilt.gradle.plugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}