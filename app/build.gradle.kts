plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jlleitschuh.gradle.ktlint)
}

android {
    namespace = "com.example.demo_autocapture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.demo_autocapture"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    lint {
        disable += "MonochromeLauncherIcon"
    }

    packaging {
        resources {
            excludes += listOf(
                "**/jnidispatch.dll",
                "**/libjnidispatch.a",
                "**/libjnidispatch.jnilib",
                "**/*.proto",
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.constraintlayout)
    implementation(libs.com.google.android.material)
    implementation(libs.com.innovatrics.dot.document)
}

ktlint {
    android = true
}
