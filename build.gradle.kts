buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", Deps.Kotlin.version))
        classpath(Deps.Android.GradlePlugin.plugin)
        classpath(Deps.SqlDelight.gradle)
        classpath(Deps.CocoaPodsExt.cocoapodsext)
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Deps.Kotlin.version}")
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version Deps.Ktlint.GradlePlugin.version
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://dl.bintray.com/ekito/koin")
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        version.set(Deps.Ktlint.version)

        enableExperimentalRules.set(true)
        verbose.set(true)
        filter {
            exclude { it.file.path.contains("build/") }
        }
    }

    afterEvaluate {
        tasks.named("check").configure {
            dependsOn(tasks.getByName("ktlintCheck"))
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
