
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mealsplanner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mealsplanner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation(libs.recyclerview)
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("com.airbnb.android:lottie:5.0.3")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("androidx.cardview:cardview:1.0.0")

//    implementation ("com.jaedongchicken:ytplayer:1.4.4")
//    implementation ("com.google.android.youtube:youtube-android-player-api:1.2.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}