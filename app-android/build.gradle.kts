plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Deps.Versions.Android.compile_sdk)
    buildToolsVersion(Deps.Versions.Android.buildToolsVersion)

    defaultConfig {
        applicationId = "com.ankushg.cocktailapp.android"
        minSdkVersion(Deps.Versions.Android.min_sdk)
        targetSdkVersion(Deps.Versions.Android.target_sdk)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ".8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Versions.Android.compose
        kotlinCompilerVersion = Deps.Versions.kotlin
    }

}

dependencies {
    implementation(project(":shared"))

    implementation(Deps.Android.core_ktx)
    implementation(Deps.Android.app_compat)
    implementation(Deps.Android.material)

    implementation(Deps.Android.Compose.ui)
    implementation(Deps.Android.Compose.material)
    implementation(Deps.Android.Compose.ui_tooling)

    implementation(Deps.Android.Lifecycle.runtime)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.Android.Test.junit)
    androidTestImplementation(Deps.Android.Test.espresso)
}