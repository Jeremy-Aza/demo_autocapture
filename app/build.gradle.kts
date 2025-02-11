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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.com.github.tgo1014.jp2ForAndroid)
    implementation(libs.com.google.android.material)
    implementation(libs.com.innovatrics.dot.document)
    implementation(libs.com.innovatrics.dot.face.detection.fast)
    implementation(libs.com.innovatrics.dot.face.expression.neutral)
}

ktlint {
    android = true
}
