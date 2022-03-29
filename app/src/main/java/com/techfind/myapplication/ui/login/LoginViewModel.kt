package com.techfind.myapplication.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techfind.myapplication.local.User
import com.techfind.myapplication.local.repository.TechfindRepository

class LoginViewModel: ViewModel() {

    private val emailActive: MutableLiveData<String> = MutableLiveData()
    val emailActiveDone: LiveData<String> = emailActive
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val status: MutableLiveData<Int> = MutableLiveData()
    val statusDone: LiveData<Int> = status

    private val userRepository = TechfindRepository()

    fun Validate(email:String,password:String){
        val user: User = userRepository.searchUser(email)
        if(user != null) {
            if (user.password == password) {
                msg.value = "Inicio sesion"
                status.value = 1
                emailActive.value = email
            } else {
                msg.value = "Usuario o Contraseña incorrecta"
                status.value = 0
            }
        }else {
            Log.d("aa","ssds")
            msg.value = "Usuario o Contraseña incorrecta"
            status.value = 0
        }

    }
}