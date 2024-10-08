package id.imdvlpr.kiosdigital.utils.ui

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.CompoundButtonCompat
import id.imdvlpr.kiosdigital.R
import id.imdvlpr.kiosdigital.databinding.LayoutTextFieldBinding

class CustomTextInput: ConstraintLayout {

    private lateinit var binding: LayoutTextFieldBinding
    private lateinit var context: Context
    private var listener: TextInputListener? = null

    enum class TYPE {
        TEXT, NUMBER, PASSWORD
    }

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
        binding = LayoutTextFieldBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_text_field, this, true))

        binding.editText.apply {
            setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> binding.constBg.setBackgroundResource(R.drawable.bg_text_field_focused)
                    false -> binding.constBg.setBackgroundResource(R.drawable.bg_text_field_default)
                }
            }
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    listener?.onTextChangeListener(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) {
                    listener?.onAfterTextChangeListener(p0.toString())
                }
            })
        }
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setHint(hint: String) {
        binding.editText.hint = hint
        binding.editText.setHintTextColor(ContextCompat.getColor(context, R.color.whiteSoft))
    }

    fun setPrefixIcon(icon: Int) {
        binding.ivPrefix.apply {
            visibility = VISIBLE
            setImageResource(icon)
        }
    }

    fun getText(): String {
        return binding.editText.text.toString()
    }

    fun setInputType(inputType: TYPE) {
        binding.editText.inputType = when (inputType) {
            TYPE.TEXT -> InputType.TYPE_CLASS_TEXT
            TYPE.NUMBER -> InputType.TYPE_CLASS_NUMBER
            TYPE.PASSWORD -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.editText.typeface = ResourcesCompat.getFont(context, R.font.nunito_regular)

        if (inputType == TYPE.PASSWORD) {
            handleEyeIcon()
        }
    }

    private fun handleEyeIcon() {
        with(binding) {
            handleCheckBoxColor()
            cbPassword.isChecked = true
            cbPassword.visibility = VISIBLE
            cbPassword.setOnCheckedChangeListener { _, isChecked ->
                editText.transformationMethod = if (isChecked) PasswordTransformationMethod() else HideReturnsTransformationMethod.getInstance()
                editText.setSelection(editText.text.length)
            }
        }
    }

    private fun handleCheckBoxColor() {
        val checkedColor = ContextCompat.getColor(context, R.color.white)
        val uncheckedColor = ContextCompat.getColor(context, R.color.white)
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(checkedColor, uncheckedColor)
        )
        CompoundButtonCompat.setButtonTintList(binding.cbPassword, colorStateList)
    }

    fun setListener(listener: TextInputListener) {
        this.listener = listener
    }

    interface TextInputListener {
        fun onTextChangeListener(text: String) {}
        fun onAfterTextChangeListener(text: String) {}
    }
}