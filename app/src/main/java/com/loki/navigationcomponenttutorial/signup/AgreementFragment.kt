package com.loki.navigationcomponenttutorial.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.loki.navigationcomponenttutorial.MainActivity
import com.loki.navigationcomponenttutorial.databinding.FragmentAgreementBinding

class AgreementFragment : Fragment() {

    private var _binding: FragmentAgreementBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { _binding = FragmentAgreementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = AgreementFragmentArgs.fromBundle(requireArguments())
        val nickname = args.nickname
        val phone = args.phone

        // 가입내용을 보여준다.
        binding.tvData.text = "Nickname: $nickname\nPhone: $phone"

        // 메인 화면으로 보낸다.
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("nickname", nickname)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}