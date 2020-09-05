plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("com.squareup.sqldelight")
}
group = "com.ankushg.cocktailapp"
version = "1.0-SNAPSHOT"

kotlin {
    android()

    // TODO: Revert to just ios() when gradle plugin can properly resolve it
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }
//    ios {
//        binaries.framework
//            baseName = "shared"
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Coroutines.core) {
                    version {
                        // TODO: remove once native-mt is merged
                        strictly(Deps.Versions.coroutines)
                    }
                }

                implementation(Deps.Koin.core)

                // SqlDelight
                implementation(Deps.SqlDelight.runtime)
                implementation(Deps.SqlDelight.coroutinesExtensions)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(Deps.Koin.test)
//                TODO: uncomment when less flaky with resolution
//                implementation(Deps.Coroutines.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.Android.material_x)
                implementation(Deps.Coroutines.android)
                implementation(Deps.SqlDelight.androidDriver)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Deps.junit)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Deps.SqlDelight.nativeDriver)
            }
        }
        val iosTest by getting {
            dependencies {

            }
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

// TODO: Revert when we switch back to ios()
//val packForXcode by tasks.creating(Sync::class) {
//    group = "build"
//    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
//    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
//    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
//    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
//    inputs.property("mode", mode)
//    dependsOn(framework.linkTask)
//    val targetDir = File(buildDir, "xcode-frameworks")
//    from({ framework.outputDirectory })
//    into(targetDir)
//}
//tasks.getByName("build").dependsOn(packForXcode)

sqldelight {
    database("CocktailsDb") {
        packageName = "com.ankushg.cocktailapp"
    }
}