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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // 레트로핏 통신 관련 라이브러리
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // RoomDB 로컬 DB 라이브러리
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-compiler:2.6.1")

    //Glide image 부착 라이브러리
    implementation ("com.github.bumptech.glide:glide:4.16.0")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")





    // Navigation
    implementation ("androidx.navigation:navigation-compose:2.4.2")

    configurations {
        implementation.get().exclude(mapOf("group" to "org.jetbrains", "module" to "annotations"))
    }




}