import data.LibConf
import data.MavenConf

plugins {
    id("io.nuclominus.android.library")
    alias(libs.plugins.detekt.analyzer)
    `maven-publish`
    signing
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
    // preconfigure defaults
    buildUponDefaultConfig = true
    // activate all available (even unstable) rules
    allRules = false
    // point to your custom config defining rules to run, overwriting default behavior
    config.setFrom("$rootDir/config/detekt/detekt.yml")
    // a way of suppressing issues before introducing detekt
    baseline = file("$projectDir/config/baseline.xml")
}

project.ext["signing.keyId"] = System.getenv("SIGN_KEY_ID")
project.ext["signing.secretKeyRingFile"] = System.getenv("SIGN_KEY")
project.ext["signing.password"] = System.getenv("SIGN_KEY_PASS")

val sourceJar by tasks.registering(Jar::class) {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sourcesJar")
}

afterEvaluate {

    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                artifact(sourceJar)

                this.groupId = MavenConf.GROUP_ID
                this.artifactId = MavenConf.ARTIFACT_ID
                this.version = LibConf.LIB_VERSION

                pom {
                    name.set(MavenConf.ARTIFACT_NAME)
                    description.set(MavenConf.DESCRIPTION)
                    url.set(MavenConf.URL)

                    licenses {
                        license {
                            name.set(MavenConf.LICENSE_NAME)
                        }
                    }

                    developers {
                        developer {
                            id.set(MavenConf.DEVELOPER_ID)
                            name.set(MavenConf.DEVELOPER_NAME)
                            email.set(MavenConf.DEVELOPER_EMAIL)
                        }
                    }

                    scm {
                        url.set(MavenConf.SCM_URL)
                    }
                }
            }
        }

        repositories {
            maven {
                name = MavenConf.MAVEN_NAME
                url = uri(MavenConf.MAVEN_URL)
                credentials {
                    username = System.getenv(MavenConf.OSS_USERNAME)
                    password = System.getenv(MavenConf.OSS_PASSWORD)
                }
            }
        }
    }

    signing {
        sign(publishing.publications)
    }
}