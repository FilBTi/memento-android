plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
}

dependencies {
    //Room region
    implementation(libs.androidx.room.common)
    //Room endregion

    //Coroutines region
    implementation(libs.kotlinx.coroutines.core)
    //Coroutines endregion
}
