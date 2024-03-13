import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR
import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

kotlin {
    jvmToolchain(17)
}



android {
    namespace = "com.example.riotapi"
    compileSdk = 34

    val properties = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "local.properties")))
    }

    val riotApiKey = properties["RIOT_APIKEY"] ?: "\"\""

    defaultConfig {
        applicationId = "com.example.riotapi"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "RIOT_API_KEY", "$riotApiKey")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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

    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }



}






dependencies {

    //코루틴 비동기 작업
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // 레트로핏 통신 관련 라이브러리
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide image 부착 라이브러리
    implementation ("com.github.bumptech.glide:glide:4.16.0")


    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.compose.ui:ui:")
    implementation ("androidx.compose.material:material")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.activity:activity-compose:1.8.2")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-tooling")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")


    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-alpha02")

    //LiveData
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.3")


    //noinspection GradleCompatible
    implementation ("com.android.support:appcompat-v7:28.0.0")

    //by viewModels로 viewModels 초기화
    implementation ("androidx.activity:activity-ktx:1.8.2")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")




}