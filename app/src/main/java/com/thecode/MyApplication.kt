package com.thecode

import android.app.Application
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Get the instance of the Firebase App
        val firebaseApp = FirebaseApp.getInstance()
    }
}