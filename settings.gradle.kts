pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SpotifyJetpackCompose"
include(":app")
include(":common:base")
include(":common:core")
include(":common:test_util")
include(":common:ui")
include(":feature:home")
include(":feature:search")
include(":feature:yourlibrary")
