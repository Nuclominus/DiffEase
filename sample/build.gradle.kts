plugins {
    id("io.nuclominus.android.application")
    alias(libs.plugins.detekt.analyzer)
}

android {
    namespace = "com.nuclominus.diffease.sample"

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

detekt {
    source.setFrom("src/main/java")
    config.setFrom("../config/detekt/detekt.yaml")
    buildUponDefaultConfig = true
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