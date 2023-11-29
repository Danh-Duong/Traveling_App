plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.traveling_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.traveling_app"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.github.smarteist:autoimageslider:1.3.9")
    implementation("com.chauthai.swipereveallayout:swipe-reveal-layout:1.4.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.flexbox:flexbox:3.0.0")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-database")
    implementation ("com.facebook.android:facebook-android-sdk:5.15.3")
    implementation ("com.facebook.android:facebook-android-sdk:[4,5)")
    implementation ("com.google.firebase:firebase-auth:22.0.0")
    implementation ("com.facebook.android:facebook-login:12.1.0")

    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.android.gms:play-services-auth:20.7.0")

}