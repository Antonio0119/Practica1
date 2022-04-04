package com.techfind.myapplication.ui.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techfind.myapplication.local.repository.TechfindRepository
import com.techfind.myapplication.server.serverrepository.UserServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegisterViewModel: ViewModel() {
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg
    private val status: MutableLiveData<Int> = MutableLiveData()
    val statusDone: LiveData<Int> = status
    private val userRepository = TechfindRepository()


    private fun emailValidation(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validation(name: String, email: String, password: String, repeatPassword: String, document: String, number: String)
    {
        status.value = 0
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            && repeatPassword.isNotEmpty() && document.isNotEmpty() && number.isNotEmpty()
        ) {
            if (password.length >= 6) {
                // Si las contraseñas son iguales se verifica la validación del email
                if (password == repeatPassword) {

                    // Si la validación es true se indica un registro exitoso
                    if (emailValidation(email)) {

                        status.value = 1
                        msg.value = "Registro exitoso"

                    } else {
                        //Si el email es invalido
                        msg.value = "Email Inválido"
                    }
                } else {
                    //Si las contrasenas no son iguales
                    msg.value = "Las contraseñas no son iguales"
                }
            } else {
                // si la contrasena no tiene minimo 6 digitos
                msg.value = "La contraseña debe tener mínimo 6 dígitos"
            }
        } else {
            // si no digita todos los campos
            msg.value = "Debe digitar todos los campos"
        }
    }

    fun saveUserInServer(name: String,email: String,password: String,document: Double,cel_number: Double) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("user","ViewModel")
            UserServerRepository().saveUser(
                name = name,
                email = email,
                password = password,
                document = document,
                cel_number = cel_number)
        }

    }

    fun saveUser(
        name: String,
        email: String,
        password: String,
        document: Double,
        number: Double,
    ) {
        userRepository.newUser(name,email,password,document,number)
        Log.d("newuser",name)
    }


}


