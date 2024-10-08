package id.imdvlpr.kiosdigital.screen

import android.app.Application
import com.google.firebase.FirebaseApp

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}