package id.imdvlpr.kiosdigital.screen.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
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
                viewPager.adapter = ViewPagerAdapter(this@AuthView, fragmentList)
                setCurrentItem(0, true)
                registerOnPageChangeCallback(object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        tabView.setSelectedButton(position)
                    }
                })
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