package com.loki.navigationcomponenttutorial.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.loki.navigationcomponenttutorial.R
import com.loki.navigationcomponenttutorial.databinding.FragmentNicknameBinding

class NicknameFragment : Fragment() {

    private var _binding: FragmentNicknameBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { _binding = FragmentNicknameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etNickname.requestFocus()
        binding.btnNext.setOnClickListener {
            val nickname = binding.etNickname.text.toString()
            if (nickname.isEmpty()) {
                Snackbar.make(binding.btnNext, "Nickname must not be empty.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bundle = Bundle()
            bundle.putString("nickname", binding.etNickname.text.toString())

            findNavController().navigate(
                R.id.action_nicknameFragment_to_phoneFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}