plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Deps.Android.Sdk.compile_version)

    defaultConfig {
        applicationId = "com.ankushg.cocktailapp.android"
        minSdkVersion(Deps.Android.Sdk.min_version)
        targetSdkVersion(Deps.Android.Sdk.target_version)

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
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Android.Compose.version
        kotlinCompilerVersion = Deps.Kotlin.version
    }

}

dependencies {
    implementation(project(":shared"))

    implementation(Deps.Android.Core.ktx)
    implementation(Deps.Android.AppCompat.appcompat)

    implementation(Deps.Android.Compose.runtime)
    implementation(Deps.Android.Compose.foundation)
    implementation(Deps.Android.Compose.layout)
    implementation(Deps.Android.Compose.material)
    implementation(Deps.Android.Compose.animation)
    implementation(Deps.Android.Compose.iconsExtended)
    implementation(Deps.Android.Compose.ui_tooling)


    implementation(Deps.Android.Accompanist.coil)

    implementation(Deps.Android.Lifecycle.runtime)

    testImplementation(Deps.Junit.junit)
    androidTestImplementation(Deps.Android.Test.Ext.junit)
    androidTestImplementation(Deps.Android.Espresso.core)
}