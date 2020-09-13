plugins {
    kotlin("multiplatform")
    id("co.touchlab.native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()
    // Revert to just ios() when gradle plugin can properly resolve it
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    version = "1.0"

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common", Deps.Versions.kotlin))

        implementation(Deps.SqlDelight.runtime)

        // Ktor
        implementation(Deps.Ktor.commonCore)
        implementation(Deps.Ktor.commonJson)
        implementation(Deps.Ktor.commonLogging)
        implementation(Deps.Ktor.commonSerialization)

        implementation(Deps.Coroutines.common)
        implementation(Deps.stately)
        implementation(Deps.multiplatformSettings)
        implementation(Deps.koinCore)
        api(Deps.kermit)
    }

    sourceSets["commonTest"].dependencies {
        implementation(Deps.KotlinTest.common)
        implementation(Deps.KotlinTest.annotations)

        implementation(Deps.multiplatformSettingsTest)

        implementation(Deps.koinTest)

        // Karmok is an experimental library which helps with mocking interfaces
        implementation(Deps.karmok)
    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Deps.Versions.kotlin))
        implementation(Deps.SqlDelight.driverAndroid)
        implementation(Deps.Coroutines.android)

        implementation(Deps.Ktor.jvmCore)
        implementation(Deps.Ktor.jvmJson)
        implementation(Deps.Ktor.jvmLogging)
        implementation(Deps.Ktor.androidSerialization)
        implementation(Deps.Ktor.androidCore)
    }

    sourceSets["androidTest"].dependencies {
        implementation(Deps.KotlinTest.jvm)
        implementation(Deps.KotlinTest.junit)

        implementation(Deps.Android.Test.core)
        implementation(Deps.Android.Test.junit)
        implementation(Deps.Android.Test.runner)
        implementation(Deps.Android.Test.rules)

        implementation(Deps.Coroutines.test)

        implementation(Deps.robolectric)
    }

    sourceSets["iosMain"].dependencies {
        implementation(Deps.Coroutines.common) {
            version {
                strictly(Deps.Versions.coroutines)
            }
        }

        implementation(Deps.SqlDelight.driverIos)
        implementation(Deps.Ktor.ios)
        implementation(Deps.koinCore)
    }

    cocoapodsext {
        summary = "Common library for CocktailApp"
        homepage = "https://github.com/ankushg/CocktailApp"
        framework {
            isStatic = false
            export(Deps.kermit)
            transitiveExport = true
        }
    }
}

android {
    compileSdkVersion(Deps.Versions.Android.compile_sdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Deps.Versions.Android.min_sdk)
        targetSdkVersion(Deps.Versions.Android.target_sdk)
        
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

sqldelight {
    database("CocktailsDb") {
        packageName = "com.ankushg.cocktailapp"
    }
}
