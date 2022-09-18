package com.loki.navigationcomponenttutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loki.navigationcomponenttutorial.databinding.ActivityMainBinding
import com.loki.navigationcomponenttutorial.signup.SignUpActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 닉네임을 넘겨준게 있다면 보여준다.
        val nickname = intent.getStringExtra("nickname") ?: ""
        if (nickname.isNotEmpty()) {
            binding.tvAccount.text = "Hi, $nickname"
        }

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}