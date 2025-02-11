import java.net.URI

pluginManagement {
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
        maven {
            url = URI("https://maven.innovatrics.com/releases")
        }
        maven {
            url = URI("https://jitpack.io")
        }
    }
}

rootProject.name = "demo_autocapture"
include(":app")
 