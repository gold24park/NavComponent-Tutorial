package com.loki.navigationcomponenttutorial.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.loki.navigationcomponenttutorial.R
import com.loki.navigationcomponenttutorial.databinding.FragmentPhoneBinding

/**
 * 휴대폰번호 입력 화면
 */
class PhoneFragment : Fragment() {


    private var _binding: FragmentPhoneBinding? = null

    private val binding get() = _binding!!

    private var nickname: String = ""
    private var phone: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nickname = PhoneFragmentArgs.fromBundle(requireArguments()).nickname

        binding.btnNext.setOnClickListener {
            phone = binding.etPhone.text.toString()

            if (phone.isEmpty()) {
                Snackbar.make(binding.btnNext, "PhoneNumber must not be empty.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bundle = Bundle()
            bundle.putString("phone", binding.etPhone.text.toString())

            findNavController().navigate(
                R.id.action_phoneFragment_to_verifyCodeFragment,
                bundle
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!

        currentBackStackEntry.savedStateHandle
            .getLiveData<Boolean>(VerifyCodeFragment.PHONE_VERIFIED)
            .observe(currentBackStackEntry) { verified ->
                if (verified) {
                    val bundle = Bundle()
                    bundle.putString("nickname", nickname)
                    bundle.putString("phone", phone)
                    navController.navigate(
                        R.id.action_phoneFragment_to_agreementFragment,
                        bundle
                    )
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}