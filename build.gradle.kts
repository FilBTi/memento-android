import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
    alias(libs.plugins.androidLibrary) apply false
}


allprojects {

    apply(plugin = rootProject.project.libs.plugins.detekt.get().pluginId)
    apply(plugin = "base")

    dependencies {
        detektPlugins(rootProject.project.libs.util.detektPlugin)
        detektPlugins(rootProject.project.libs.util.detektFormatting)
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = JavaVersion.VERSION_17.toString()
        setSource(files("src"))
        exclude("**/androidTest/**")
        exclude("**/assets/**")
        exclude("**/res/**")
        exclude("**/test/**")
        exclude("**/test-ui-*/**")

    }
    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    detekt {
        toolVersion = rootProject.project.libs.versions.detekt.get()
        buildUponDefaultConfig = true
        autoCorrect = true
    }
}
tasks.register("projectClean", Delete::class) {
    delete(setOf(rootProject.buildDir))
}
