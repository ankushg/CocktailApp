object Deps {
    object Kotlin {
        const val version = "1.4.10"

        object Test {
            const val common = "org.jetbrains.kotlin:kotlin-test-common:$version"
            const val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:$version"
            const val jvm = "org.jetbrains.kotlin:kotlin-test:$version"
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
        }
    }

    object Ktlint {
        const val version = "0.38.1"

        object GradlePlugin {
            const val version = "9.4.0"
        }
    }

    object Junit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object Koin {
        private const val version = "3.0.0-alpha-4"

        const val core = "org.koin:koin-core:$version"
        const val test = "org.koin:koin-test:$version"
    }

    object MultiplatformSettings {
        private const val version = "0.6.1"

        const val core = "com.russhwolf:multiplatform-settings:$version"
        const val test = "com.russhwolf:multiplatform-settings-test:$version"
    }

    object Coroutines {
        const val version = "1.3.9-native-mt"

        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object SqlDelight {
        private const val version = "1.4.3"

        const val gradle = "com.squareup.sqldelight:gradle-plugin:$version"
        const val runtime = "com.squareup.sqldelight:runtime:$version"
        const val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:$version"
        const val driverIos = "com.squareup.sqldelight:native-driver:$version"
        const val driverAndroid = "com.squareup.sqldelight:android-driver:$version"
    }

    object Ktor {
        private const val version = "1.4.0"

        const val commonCore = "io.ktor:ktor-client-core:$version"
        const val commonJson = "io.ktor:ktor-client-json:$version"
        const val commonLogging = "io.ktor:ktor-client-logging:$version"
        const val commonSerialization = "io.ktor:ktor-client-serialization:$version"

        const val jvmCore = "io.ktor:ktor-client-core-jvm:$version"
        const val androidCore = "io.ktor:ktor-client-okhttp:$version"
        const val jvmJson = "io.ktor:ktor-client-json-jvm:$version"
        const val jvmLogging = "io.ktor:ktor-client-logging-jvm:$version"
        const val androidSerialization = "io.ktor:ktor-client-serialization-jvm:$version"

        const val ios = "io.ktor:ktor-client-ios:$version"
        const val iosCore = "io.ktor:ktor-client-core-native:$version"
        const val iosJson = "io.ktor:ktor-client-json-native:$version"
        const val iosLogging = "io.ktor:ktor-client-logging-native:$version"
    }

    object Serialization {
        private const val version = "1.0.0-RC"

        const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:$version"
    }

    object Android {
        object Sdk {
            const val min_version = 21
            const val target_version = 30
            const val compile_version = 30
        }

        object GradlePlugin {
            private const val version = "4.2.0-alpha10"
            const val plugin = "com.android.tools.build:gradle:$version"
        }

        object Core {
            private const val version = "1.5.0-alpha02"
            const val ktx = "androidx.core:core-ktx:$version"
        }

        object AppCompat {
            private const val version = "1.2.0"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object Material {
            private const val version = "1.1.0"
            const val material = "com.google.android.material:material:$version"
        }

        object Compose {
            const val version = "1.0.0-alpha02"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val ui_tooling = "androidx.ui:ui-tooling:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
        }

        object Lifecycle {
            private const val version = "2.3.0-alpha07"

            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewmodel = "android.arch.lifecycle:viewmodel:$version"
            const val viewmodel_extensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedata = "android.arch.lifecycle:livedata:$version"
            const val extension = "android.arch.lifecycle:extensions:$version"
        }

        object Test {
            private const val version = "1.2.0"

            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"

                const val junit = "androidx.test.ext:junit:$version"
            }
        }

        object Espresso {
            private const val version = "3.3.0"

            const val core = "androidx.test.espresso:espresso-core:$version"
        }

        object Accompanist {
            private const val version = "0.2.1"

            const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
        }
    }

    object Stately {
        private const val version = "1.1.0"

        const val common = "co.touchlab:stately-common:$version"
    }

    object CocoaPodsExt {
        private const val version = "0.11"

        const val cocoapodsext = "co.touchlab:kotlinnativecocoapods:$version"
    }

    object Kermit {
        private const val version = "0.1.8"

        const val kermit = "co.touchlab:kermit:${version}"
    }

    object Karmok {
        private const val version = "0.1.8"
        const val library = "co.touchlab:karmok-library:$version"
    }

    object Robolectric {
        private const val version = "4.3.1"
        const val robolectric = "org.robolectric:robolectric:$version"
    }
}
