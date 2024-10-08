plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.secret.gradle)
    alias(libs.plugins.google.services)
}

android {
    namespace = "id.imdvlpr.kiosdigital"
    compileSdk = 34

    defaultConfig {
        applicationId = "id.imdvlpr.kiosdigital"
        minSdk = 26
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions.add("env")
    productFlavors {
        create("dev") {
            dimension = "env"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            buildConfigField("String", "BASE_URL", project.findProperty("api_sandbox") as String)
            versionCode = 1
            versionName = "1.0"
        }
        create("prod") {
            buildConfigField("String", "BASE_URL", project.findProperty("api_production") as String)
            dimension = "env"
            versionCode = 1
            versionName = "1.0"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    "devImplementation"(libs.chunker.std)
    "prodImplementation"(libs.chunker.no.op)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.bundles.network)
    implementation(libs.security.aescrypt)
    implementation(libs.view.pager2)

    implementation(libs.koin.android)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}