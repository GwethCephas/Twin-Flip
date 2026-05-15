import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrainsKotlinSerialization)
}
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")

if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

android {
    namespace = "com.twinflip"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.twinflip"
        minSdk = 24
        targetSdk = 36
        versionCode = 6
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
        val admobAppId = localProperties.getProperty("ADMOB_APP_ID")
            ?: System.getenv("ADMOB_APP_ID")
            ?: "ca-app-pub-3940256099942544~3347511713"

        manifestPlaceholders["admobAppId"] = admobAppId

        val bannerId = localProperties.getProperty("BANNER_AD_UNIT_ID")
            ?: System.getenv("BANNER_AD_UNIT_ID") ?: ""

        val interId = localProperties.getProperty("INTERSTITIAL_AD_UNIT_ID")
            ?: System.getenv("INTERSTITIAL_AD_UNIT_ID") ?: ""

        buildConfigField("String", "BANNER_AD_UNIT_ID", "\"$bannerId\"")
        buildConfigField("String", "INTERSTITIAL_AD_UNIT_ID", "\"$interId\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.text.google.fonts)

    testImplementation(libs.mockk)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Koin
    implementation(libs.bundles.koin)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    //google gson
    implementation(libs.gson)

    // coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    // datastore
    implementation(libs.datastore.preferences)
    implementation(libs.datastore.core)

    //Media player
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.ui)
    implementation(libs.media3.session)

    //konfetti
    implementation(libs.konfetti)

    //Google Admob
    implementation(libs.play.services.ads)
}