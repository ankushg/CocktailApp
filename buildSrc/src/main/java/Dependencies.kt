object Deps {

    object Versions {
        val kotlin = "1.4.10"

        val ktlint_gradle_plugin = "9.4.0"
        val ktlint = "0.38.1"

        val sqlDelight = "1.4.3"
        val ktor = "1.4.0"
        val stately = "1.1.0"
        val multiplatformSettings = "0.6.1"
        val coroutines = "1.3.9-native-mt"
        val koin = "3.0.1-alpha-2"
        val serialization = "1.0.0-RC"
        val cocoapodsext = "0.11"
        val kermit = "0.1.8"
        val karmok = "0.1.8"

        val junit = "4.13"

        object Android {
            val buildToolsVersion = "30.0.0"
            val min_sdk = 21
            val target_sdk = 30
            val compile_sdk = 30

            val gradle_plugin = "4.2.0-alpha10"
            val compose = "1.0.0-alpha02"

            val app_compat = "1.2.0"
            val core_ktx = "1.3.1"
            val material = "1.1.0"

            val androidx_test = "1.2.0"
            val androidx_test_ext = "1.1.2"
            val espresso = "3.3.0"

            val lifecycle = "2.3.0-alpha07"
            val robolectric = "4.3.1"
        }
    }

    object Android {
        val gradle_plugin = "com.android.tools.build:gradle:${Versions.Android.gradle_plugin}"
        val core_ktx = "androidx.core:core-ktx:${Versions.Android.core_ktx}"
        val app_compat = "androidx.appcompat:appcompat:${Versions.Android.app_compat}"
        val material = "com.google.android.material:material:${Versions.Android.material}"

        object Compose {
            val ui = "androidx.compose.ui:ui:${Deps.Versions.Android.compose}"
            val material = "androidx.compose.material:material:${Deps.Versions.Android.compose}"
            val ui_tooling = "androidx.ui:ui-tooling:${Deps.Versions.Android.compose}"
        }

        object Lifecycle {
            val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycle}"
            val viewmodel = "android.arch.lifecycle:viewmodel:${Versions.Android.lifecycle}"
            val viewmodel_extensions =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
            val livedata = "android.arch.lifecycle:livedata:${Versions.Android.lifecycle}"
            val extension = "android.arch.lifecycle:extensions:${Versions.Android.lifecycle}"
        }

        object Test {
            val core = "androidx.test:core:${Versions.Android.androidx_test}"
            val junit = "androidx.test.ext:junit:${Versions.Android.androidx_test_ext}"
            val runner = "androidx.test:runner:${Versions.Android.androidx_test}"
            val rules = "androidx.test:rules:${Versions.Android.androidx_test}"
            val espresso = "androidx.test.espresso:espresso-core:${Versions.Android.espresso}"
        }
    }

    val junit = "junit:junit:${Versions.junit}"
    val stately = "co.touchlab:stately-common:${Versions.stately}"

    val cocoapodsext = "co.touchlab:kotlinnativecocoapods:${Versions.cocoapodsext}"
    val kermit = "co.touchlab:kermit:${Versions.kermit}"
    val karmok = "co.touchlab:karmok-library:${Versions.karmok}"
    val robolectric = "org.robolectric:robolectric:${Versions.Android.robolectric}"

    // Koin
    val koinCore = "org.koin:koin-core:${Versions.koin}"
    val koinTest = "org.koin:koin-test:${Versions.koin}"

    // Multiplatform Settings
    val multiplatformSettings =
        "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
    val multiplatformSettingsTest =
        "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"

    object KotlinTest {
        val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }

    object Coroutines {
        val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object Ktor {
        val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val commonSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

        val jvmCore = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        val androidCore = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        val jvmJson = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        val jvmLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
        val androidSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"

        val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        val iosCore = "io.ktor:ktor-client-core-native:${Versions.ktor}"
        val iosJson = "io.ktor:ktor-client-json-native:${Versions.ktor}"
        val iosLogging = "io.ktor:ktor-client-logging-native:${Versions.ktor}"
    }
}
