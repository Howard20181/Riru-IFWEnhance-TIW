import com.v7878.zygisk.gradle.ZygoteLoader

plugins {
    id("com.android.application")
    id("com.github.vova7878.ZygoteLoader")
}

android {
    namespace = "com.github.kr328.module"
}

dependencies {
    compileOnly(project(":hideapi"))

    implementation(libs.magic.library)
}

zygisk {
    val moduleId = "ifw-enhance-tiw"
    val moduleName = "IFW Enhance TIW"
    val moduleDescription = "Allows Intent Firewall to filter results of queryIntent(Activities/Services) APIs. Special edition for TigerInTheWall."
    val moduleAuthor = "Kr328/TigerBeanst"
    val moduleEntrypoint = "com.github.kr328.ifw.Main"
    val versionName = android.defaultConfig.versionName

    packages(ZygoteLoader.PACKAGE_SYSTEM_SERVER)
    id = "zygisk-$moduleId".replace('-', '_')
    name = "Zygisk - $moduleName"
    archiveName = "zygisk-$moduleId-$versionName"
    updateJson = "https://github.com/TigerBeanst/Riru-IFWEnhance-TIW/releases/latest/download/zygisk-$moduleId.json"
    author = moduleAuthor
    description = moduleDescription
    entrypoint = moduleEntrypoint
}
