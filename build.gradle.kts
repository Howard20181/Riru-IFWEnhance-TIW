import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension

plugins {
    val zygote = "1bcced056"
    val agp = "8.13.1"

    id("com.android.library") version agp apply false
    id("com.android.application") version agp apply false
    id("com.github.vova7878.ZygoteLoader") version zygote apply false
}

subprojects {
    plugins.withId("com.android.base") {
        extensions.configure<BaseExtension> {
            val isApp = this is AppExtension

            compileSdkVersion(33)

            defaultConfig {
                if (isApp) {
                    applicationId = "com.github.kr328.ifw"
                }

                minSdk = 26
                targetSdk = 36

                versionName = "v22TIW"
                versionCode = 22000

                if (!isApp) {
                    consumerProguardFiles("consumer-rules.pro")
                }
            }

            buildTypes {
                named("release") {
                    isMinifyEnabled = isApp
                    isShrinkResources = isApp
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}
