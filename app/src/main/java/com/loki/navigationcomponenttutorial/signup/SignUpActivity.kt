package com.loki.navigationcomponenttutorial.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loki.navigationcomponenttutorial.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}