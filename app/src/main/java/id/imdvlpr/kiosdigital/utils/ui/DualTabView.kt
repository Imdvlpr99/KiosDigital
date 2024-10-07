package id.imdvlpr.kiosdigital.utils.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import id.imdvlpr.kiosdigital.R
import id.imdvlpr.kiosdigital.databinding.LayoutDualTabViewBinding

class DualTabView: ConstraintLayout {

    private lateinit var binding: LayoutDualTabViewBinding
    private lateinit var context: Context
    private var listener: DualTabListener? = null
    private var isLeftSelected: Boolean = true

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        this.context = context
        binding = LayoutDualTabViewBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_dual_tab_view, this, true))

        handleButtonBackground()
        with(binding) {
            btnLeft.setOnClickListener {
                isLeftSelected = true
                handleButtonBackground()
                listener?.onLeftClick()
            }

            btnRight.setOnClickListener {
                isLeftSelected = false
                handleButtonBackground()
                listener?.onRightClick()
            }
        }
    }

    private fun handleButtonBackground() {
        with(binding) {
            when (isLeftSelected) {
                true -> {
                    btnLeft.apply {
                        background = ContextCompat.getDrawable(context, R.drawable.btn_primary)
                        setTextAppearance(R.style.TextSemiBold16spWhite)
                    }
                    btnRight.apply {
                        background = null
                        setTextAppearance(R.style.TextRegular16spWhite)
                    }
                }
                false -> {
                    btnRight.apply {
                        background = ContextCompat.getDrawable(context, R.drawable.btn_primary)
                        setTextAppearance(R.style.TextSemiBold16spWhite)
                    }
                    btnLeft.apply {
                        background = null
                        setTextAppearance(R.style.TextRegular16spWhite)
                    }
                }
            }
        }
    }

    fun setButtonTitle(left: String, right: String) {
        binding.btnLeft.text = left
        binding.btnRight.text = right
    }

    fun setListener(listener: DualTabListener) {
        this.listener = listener
    }

    fun setSelectedButton(position: Int) {
        if (position == 0) {
            isLeftSelected = true
            handleButtonBackground()
        } else {
            isLeftSelected = false
            handleButtonBackground()
        }
    }

    interface DualTabListener {
        fun onLeftClick()
        fun onRightClick()
    }
}