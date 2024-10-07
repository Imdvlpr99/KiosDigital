package id.imdvlpr.kiosdigital.screen.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.imdvlpr.kiosdigital.R
import id.imdvlpr.kiosdigital.databinding.FragmentRegisterBinding

class RegisterView : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            etPhoneNumber.apply {
                setTitle(getString(R.string.fill_phone_number))
                setHint(getString(R.string.hint_phone_number))
                setPrefixIcon(R.drawable.ic_user)
            }

            etPassword.apply {
                setTitle(getString(R.string.fill_password))
                setHint(getString(R.string.hint_password))
                setPrefixIcon(R.drawable.ic_lock_open)

            }

            etConfirmPassword.apply {
                setTitle(getString(R.string.fill_confirm_password))
                setHint(getString(R.string.hint_confirm_password))
                setPrefixIcon(R.drawable.ic_lock_open)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}