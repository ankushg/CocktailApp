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
        implementation(kotlin("stdlib-common", Deps.Kotlin.version))

        implementation(Deps.SqlDelight.runtime)

        // Ktor
        implementation(Deps.Ktor.commonCore)
        implementation(Deps.Ktor.commonJson)
        implementation(Deps.Ktor.commonLogging)
        implementation(Deps.Ktor.commonSerialization)

        implementation(Deps.Coroutines.common)
        implementation(Deps.Stately.common)
        implementation(Deps.MultiplatformSettings.core)
        implementation(Deps.Koin.core)
        api(Deps.Kermit.kermit)
    }

    sourceSets["commonTest"].dependencies {
        implementation(Deps.Kotlin.Test.common)
        implementation(Deps.Kotlin.Test.annotations)

        implementation(Deps.MultiplatformSettings.test)

        implementation(Deps.Koin.test)

        // Karmok is an experimental library which helps with mocking interfaces
        implementation(Deps.Karmok.library)
    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Deps.Kotlin.version))
        implementation(Deps.SqlDelight.driverAndroid)
        implementation(Deps.Coroutines.android)

        implementation(Deps.Ktor.jvmCore)
        implementation(Deps.Ktor.jvmJson)
        implementation(Deps.Ktor.jvmLogging)
        implementation(Deps.Ktor.androidSerialization)
        implementation(Deps.Ktor.androidCore)
    }

    sourceSets["androidTest"].dependencies {
        implementation(Deps.Kotlin.Test.jvm)
        implementation(Deps.Kotlin.Test.junit)

        implementation(Deps.Android.Test.core)
        implementation(Deps.Android.Test.runner)
        implementation(Deps.Android.Test.rules)
        implementation(Deps.Android.Test.Ext.junit)

        implementation(Deps.Coroutines.test)

        implementation(Deps.Robolectric.robolectric)
    }

    sourceSets["iosMain"].dependencies {
        implementation(Deps.Coroutines.common) {
            version {
                strictly(Deps.Coroutines.version)
            }
        }

        implementation(Deps.SqlDelight.driverIos)
        implementation(Deps.Ktor.ios)
        implementation(Deps.Koin.core)
    }

    cocoapodsext {
        summary = "Common library for CocktailApp"
        homepage = "https://github.com/ankushg/CocktailApp"
        framework {
            isStatic = false
            export(Deps.Kermit.kermit)
            transitiveExport = true
        }
    }
}

android {
    compileSdkVersion(Deps.Android.Sdk.compile_version)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Deps.Android.Sdk.min_version)
        targetSdkVersion(Deps.Android.Sdk.target_version)

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
