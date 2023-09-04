// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.0.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath ("com.google.gms:google-services:4.3.15")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath ("com.google.firebase:perf-plugin:1.4.2")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.41" apply false

}