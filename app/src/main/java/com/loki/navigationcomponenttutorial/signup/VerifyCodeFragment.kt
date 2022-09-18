package com.loki.navigationcomponenttutorial.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.loki.navigationcomponenttutorial.databinding.FragmentVerifyCodeBinding

/**
 * 인증번호 입력화면
 */
class VerifyCodeFragment : Fragment() {

    private lateinit var savedStateHandle: SavedStateHandle
    private var _binding: FragmentVerifyCodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 이전 화면(PhoneFragment)의 savedStateHandle
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[PHONE_VERIFIED] = false

        // 넘어온 phone이 이미 확인된 거라면 PASS
        val phone = VerifyCodeFragmentArgs.fromBundle(requireArguments()).phone
        if (viewModel.isVerifiedPhoneNumber(phone)) {
            onVerificationSucceed(phone)
        }

        binding.btnNext.setOnClickListener {
            val code = binding.etCode.text.toString().trim()
            if (code == TEST_CODE) {
                onVerificationSucceed(phone)
            } else {
                Snackbar.make(binding.btnNext, "Incorrect verification code.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // 인증성공, PhoneFragment로 돌아간다.
    private fun onVerificationSucceed(phone: String) {
        savedStateHandle[PHONE_VERIFIED] = true
        viewModel.addVerifiedPhoneNumber(phone)
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TEST_CODE = "1234" // 테스트용 인증번호
        const val PHONE_VERIFIED = "PHONE_VERIFIED"
    }
}