plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.detekt.analyzer)
    `maven-publish`
    signing
}

android {
    namespace = "com.nuclominus.diffease"
    compileSdk = rootProject.extra["maxSdkVersion"] as Int
    defaultConfig {
        minSdk = rootProject.extra["minSdkVersion"] as Int
        consumerProguardFiles("proguard-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
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
    api(libs.recyclerviewSelection)
    api(libs.recyclerview)
}

detekt {
    source.setFrom("src/main/kotlin")
    config.setFrom("../config/detekt/detekt.yaml")
    buildUponDefaultConfig = true
}

project.ext["signing.keyId"] = System.getenv("SIGN_KEY_ID")
project.ext["signing.secretKeyRingFile"] = System.getenv("SIGN_KEY")
project.ext["signing.password"] = System.getenv("SIGN_KEY_PASS")

val sourceJar by tasks.registering(Jar::class) {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sourcesJar")
}

afterEvaluate {
    val groupId by extra { "io.github.nuclominus" }
    val artifactId by extra { "diffease" }
    val version by extra { "1.2.0" }

    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                artifact(sourceJar)

                this.groupId = groupId
                this.artifactId = artifactId
                this.version = version

                pom {
                    name.set("DiffEase")
                    description.set("A simple description and use of an adapter based on DiffUtils")
                    url.set("https://github.com/Nuclominus/DiffEase")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("nuclominus")
                            name.set("Roman Kosko")
                            email.set("9DGRoman@gmail.com")
                        }
                    }

                    scm {
                        url.set("https://github.com/Nuclominus/DiffEase")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "sonatypeStaging"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = System.getenv("OSS_USERNAME")
                    password = System.getenv("OSS_PASSWORD")
                }
            }
        }
    }

    signing {
        sign(publishing.publications)
    }
}