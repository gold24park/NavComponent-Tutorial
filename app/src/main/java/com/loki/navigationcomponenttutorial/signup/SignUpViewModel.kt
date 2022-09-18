package com.loki.navigationcomponenttutorial.signup

import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private var verifiedPhoneNumbers = mutableSetOf<String>()

    fun addVerifiedPhoneNumber(phone: String) {
        verifiedPhoneNumbers.add(phone)
    }

    fun isVerifiedPhoneNumber(phone: String): Boolean {
        return verifiedPhoneNumbers.contains(phone)
    }

}