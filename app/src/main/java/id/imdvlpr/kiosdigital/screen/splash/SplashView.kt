package id.imdvlpr.kiosdigital.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import id.imdvlpr.kiosdigital.MainActivity
import id.imdvlpr.kiosdigital.R
import id.imdvlpr.kiosdigital.databinding.ActivityAuthBinding
import id.imdvlpr.kiosdigital.databinding.ActivitySplashBinding
import id.imdvlpr.kiosdigital.screen.auth.AuthView
import id.imdvlpr.kiosdigital.utils.extension.Constants
import id.imdvlpr.kiosdigital.utils.extension.getBooleanFromPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashView : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(3000)
            val targetActivity = if (getBooleanFromPref(Constants.PREF.IS_LOGGED_IN)) MainActivity::class.java else AuthView::class.java
            startActivity(Intent(this@SplashView, targetActivity))
            finish()
        }

        initView()
    }

    private fun initView() {
        with(binding) {

        }
    }
}