buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", Deps.Versions.kotlin))
        classpath(Deps.Android.android_gradle_plugin)
        classpath(Deps.SqlDelight.gradle)
    }
}
group = "com.ankushg.cocktailapp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
