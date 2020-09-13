buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", Deps.Versions.kotlin))
        classpath(Deps.Android.gradle_plugin)
        classpath(Deps.SqlDelight.gradle)
        classpath(Deps.cocoapodsext)
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Deps.Versions.kotlin}")
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version Deps.Versions.ktlint_gradle_plugin
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        // maven(url = "https://dl.bintray.com/ekito/koin") TODO: revert when Koin is available
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        version.set(Deps.Versions.ktlint)
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
