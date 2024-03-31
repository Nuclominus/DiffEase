plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.ksp)
}

android {
    compileSdk = rootProject.extra["maxSdkVersion"] as Int
    namespace = "com.nuclominus.diffease.sample"

    defaultConfig {
        applicationId = "com.nuclominus.diffease.sample"
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["maxSdkVersion"] as Int
        versionCode = 3
        versionName = rootProject.extra["sampleVersion"] as String
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildToolsVersion = rootProject.extra["buildTools"] as String
    }

    buildFeatures {
        viewBinding = true
        dataBinding = false
        compose = false
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xcontext-receivers",
        )
    }

}

dependencies {
    // Fix knowing issue with new version of androidx.core
    //https://youtrack.jetbrains.com/issue/KT-54136/Duplicated-classes-cause-build-failure-if-a-dependency-to-kotlin-stdlib-specified-in-an-android-project#focus=Comments-27-6550645.0-0
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    api(libs.androidx.core)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.navigation.fragment)
    api(libs.navigation.fragment.ui)
    api(libs.diffease)

    // Hilt
    api(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // ViewBinding delegate
    api(libs.viewbindingDelegate)
}