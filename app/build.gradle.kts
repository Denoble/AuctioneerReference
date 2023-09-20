plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
     id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
    id ("dagger.hilt.android.plugin")
    id ("com.google.firebase.firebase-perf")
    //id ("com.apollographql.apollo3")
    id ("com.ncorti.ktfmt.gradle") version ("0.10.0")
}

android {
    namespace = "com.gevcorst.auctioneerreference"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gevcorst.auctioneerreference"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("com.apollographql.apollo3:apollo-runtime:4.0.0-alpha.2")
    implementation ("com.google.android.material:material:1.9.0")
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha12")
    kapt("com.google.dagger:hilt-compiler:2.48")
    //firebase auth
    implementation( platform("com.google.firebase:firebase-bom:32.2.3"))
    //implementation ("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.firebase:firebase-crashlytics-ktx")
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-firestore-ktx")
    implementation ("com.google.firebase:firebase-perf-ktx")
    implementation ("com.google.firebase:firebase-config-ktx")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.apollographql.apollo3:apollo-runtime:4.0.0-alpha.2")
    //    firebase realtime database
    implementation ("com.google.firebase:firebase-database:20.2.2")
    // FirebaseUI for Firebase Realtime Database
    implementation ("com.firebaseui:firebase-ui-database:8.0.2")
    implementation ("androidx.navigation:navigation-compose:2.7.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    implementation ("androidx.preference:preference-ktx:1.2.1")
    //glide Image painter
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.github.bumptech.glide:compose:1.0.0-alpha.3")
    //coil async image loader
    implementation("io.coil-kt:coil-compose:2.4.0")
    //preference datastore
    implementation("androidx.datastore:datastore-core:1.1.0-alpha04")
    //implementation("androidx.datastore:datastore-preferences:1.1.0-alpha04")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}