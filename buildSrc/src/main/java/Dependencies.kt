object Deps {
    object Versions {
        // region Externally Consumed
        object Android {
            val min_sdk = 21
            val target_sdk = 30
            val compile_sdk = 30
        }

        val kotlin = "1.4.0"
        // endregion
        internal val junit = "4.13"
        // Third-party Libs
        internal val sqlDelight = "1.4.3"
        internal val koin = "3.0.1-alpha-2"
    object Android {
        val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
        val app_compat_x = "androidx.appcompat:appcompat:1.2.0"
        val material_x = "com.google.android.material:material:1.2.1"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"
        object Test {
        }
    }

    val junit = "junit:junit:${Versions.junit}"
    object Koin {
        val core = "org.koin:koin-core:${Versions.koin}"
        val test = "org.koin:koin-test:${Versions.koin}"
    }
    object SqlDelight{
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }
    object Ktor {
        val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val commonSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

        val okhttpClient = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        val iosClient ="io.ktor:ktor-client-ios:${Versions.ktor}"
    }
}