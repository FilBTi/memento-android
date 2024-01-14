@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.puroblast.memento"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.puroblast.memento"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":common-resources"))
    implementation(project(":domain-memento"))
    implementation(project(":feature-auth"))
    implementation(project(":feature-folders"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    //Dagger region
    implementation(libs.dagger)
    //Dagger endregion

    //Room region
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.common)
    //Room endregion

    //Navigation region
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    //Navigation endregion

    kapt(libs.dagger.compiler)
    kapt(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
