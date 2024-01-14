plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.puroblast.feature_folders"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    //ViewBinding region
    implementation(libs.viewbindingpropertydelegate.noreflection)
    //ViewBinding endregion

    //Navigation region
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    //Navigation endregion

    //Coroutines region
    implementation(libs.kotlinx.coroutines.core)
    //Coroutines endregion

    //Dagger region
    implementation(libs.dagger)
    //Dagger endregion

    kapt(libs.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
