package data

import org.gradle.api.JavaVersion

object AndroidAppConf {
    const val APP_VERSION: String = "1.0.2"
    const val VERSION_CODE: Int = 8
    const val NAMESPACE: String = "com.nuclominus.diffease.sample"
    const val COMPILE_SDK: Int = 34
    const val MIN_SDK: Int = 21
    val javaVersion: JavaVersion = JavaVersion.VERSION_19
}

object LibConf {
    const val LIB_VERSION: String = "1.2.2"
    const val NAMESPACE: String = "com.nuclominus.diffease"
}

object MavenConf {
    const val GROUP_ID: String = "io.github.nuclominus"
    const val ARTIFACT_ID: String = "diffease"
    const val LIB_VERSION: String = LibConf.LIB_VERSION
    const val ARTIFACT_NAME: String = "DiffEase"
    const val DESCRIPTION: String = "A simple description and use of an adapter based on DiffUtils"
    const val URL: String = "https://github.com/Nuclominus/DiffEase"
    const val LICENSE_NAME: String = "The MIT License"
    const val DEVELOPER_ID: String = "Nuclominus"
    const val DEVELOPER_NAME: String = "Roman Kosko"
    const val DEVELOPER_EMAIL: String = "9DGRoman@gmail.com"
    const val SCM_URL: String = "https://github.com/Nuclominus/DiffEase"
    const val MAVEN_NAME: String = "sonatypeStaging"
    const val MAVEN_URL: String = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
    const val OSS_USERNAME: String = "OSS_USERNAME"
    const val OSS_PASSWORD: String = "OSS_PASSWORD"
}
