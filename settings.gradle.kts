rootProject.name = "IFW Enhance"

include(":module", ":hideapi")

pluginManagement {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
        mavenLocal()
    }
    versionCatalogs {
        create("libs") {
            val magic = "1.9"

            library("magic-library", "com.github.kr328.magic", "library").version(magic)
        }
    }
}
