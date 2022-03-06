package com.techfind.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val email: MutableLiveData<String> = MutableLiveData()
    val emailDone: LiveData<String> = email
    private val password: MutableLiveData<String> = MutableLiveData()
    val passwordDone: LiveData<String> = email
}