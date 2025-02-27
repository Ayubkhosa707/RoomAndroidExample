plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false // For room
    id("kotlin-kapt")
}

android {
    namespace = "com.ayub.khosa.roomandroidexample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ayub.khosa.roomandroidexample"
        minSdk = 30
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("com.google.code.gson:gson:2.12.1")
    /*************************************************************
     *  Proto Data Store
     *************************************************************/
    implementation("androidx.datastore:datastore-core:1.1.3")
    implementation  ("com.google.protobuf:protobuf-javalite:3.18.0")



    // Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    //  implementation ("android.arch.persistence.room:runtime:2.3.0")
    kapt ("androidx.room:room-compiler:2.6.1" )
    annotationProcessor("androidx.room:room-compiler:2.6.1")
}