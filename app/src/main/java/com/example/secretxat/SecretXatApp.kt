package com.example.secretxat

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SecretXatApp : Application(){

    override fun onCreate() {
        super.onCreate()

        //inicialitzem Firebase aqui perque sempre es crida abans que qualsevol activitat
        FirebaseApp.initializeApp(this)


    }

}