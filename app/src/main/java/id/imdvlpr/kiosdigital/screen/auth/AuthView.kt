package id.imdvlpr.kiosdigital.screen.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.imdvlpr.kiosdigital.R
import id.imdvlpr.kiosdigital.databinding.ActivityAuthBinding
import id.imdvlpr.kiosdigital.utils.ui.DualTabView
import id.imdvlpr.kiosdigital.utils.ui.ViewPagerAdapter

class AuthView : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val fragmentList = mutableListOf(
        LoginView(),
        RegisterView()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        with(binding) {
            viewPager.apply {
                isUserInputEnabled = false
                viewPager.adapter = ViewPagerAdapter(this@AuthView, fragmentList)
            }

            tabView.apply {
                setButtonTitle(
                    getString(R.string.login_title),
                    getString(R.string.register_title)
                )
                setListener(object : DualTabView.DualTabListener {
                    override fun onLeftClick() {
                        viewPager.setCurrentItem(0, true)
                    }

                    override fun onRightClick() {
                        viewPager.setCurrentItem(1, true)
                    }

                })
            }
        }
    }
}