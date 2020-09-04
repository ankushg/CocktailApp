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
    object Android {
        val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
        val app_compat_x = "androidx.appcompat:appcompat:1.2.0"
        val material_x = "com.google.android.material:material:1.2.1"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"
        object Test {
        }
    }

    val junit = "junit:junit:${Versions.junit}"
}