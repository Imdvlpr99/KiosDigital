package id.imdvlpr.kiosdigital.screen.auth

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.imdvlpr.kiosdigital.R
import id.imdvlpr.kiosdigital.databinding.FragmentLoginBinding
import id.imdvlpr.kiosdigital.utils.extension.btnPrimaryBackgroundTint
import id.imdvlpr.kiosdigital.utils.ui.CustomTextInput

class LoginView : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            etUsername.apply {
                setTitle(getString(R.string.fill_phone_number))
                setHint(getString(R.string.hint_phone_number))
                setPrefixIcon(R.drawable.ic_user)
                setInputType(CustomTextInput.TYPE.NUMBER)
                setListener(object : CustomTextInput.TextInputListener {
                    override fun onAfterTextChangeListener(text: String) {
                        validateInput()
                    }
                })
            }

            etPassword.apply {
                setTitle(getString(R.string.fill_password))
                setHint(getString(R.string.hint_password))
                setPrefixIcon(R.drawable.ic_lock_open)
                setInputType(CustomTextInput.TYPE.PASSWORD)
                setListener(object : CustomTextInput.TextInputListener {
                    override fun onAfterTextChangeListener(text: String) {
                        validateInput()
                    }
                })
            }

            btnLogin.apply {
                backgroundTintList = requireContext().btnPrimaryBackgroundTint()
            }
        }

    }

    private fun validateInput() {
        binding.btnLogin.isEnabled = binding.etUsername.getText().isNotEmpty() &&
                binding.etPassword.getText().isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}