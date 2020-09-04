plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = "com.ankushg.cocktailapp"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":shared"))

    implementation(Deps.Android.material_x)
    implementation(Deps.Android.app_compat_x)
    implementation(Deps.Android.constraintlayout)
}
android {
    compileSdkVersion(Deps.Versions.Android.compile_sdk)
    defaultConfig {
        applicationId = "com.ankushg.cocktailapp.app-android"
        minSdkVersion(Deps.Versions.Android.min_sdk)
        targetSdkVersion(Deps.Versions.Android.target_sdk)

        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}