package com.techfind.myapplication.ui.profile

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg
    private val status: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val statusDone: LiveData<ArrayList<String>> = status

    fun validation(name: String, email: String, password: String, document: String, number: String) {
        status.value?.clear()
        val aux :MutableList<String> = mutableListOf()
        aux.clear()

        if (name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty()
            || document.isNotEmpty() || number.isNotEmpty()
        ) {
            Log.d("1","Algun dato")
            if(name.isNotEmpty()){
                aux.add("name")
            }
            if (email.isNotEmpty()){
                if(emailValidation(email)) {
                    aux.add("email")
                }
                else {
                    aux.clear()
                    msg.value = "Debe digitar un correo electronico valido"
                    return

                }
            }
            if(password.isNotEmpty()) {
                if (password.length >= 6) {
                    aux.add("password")
                } else {
                    aux.clear()
                    msg.value = "La contraseña debe tener mínimo 6 dígitos"
                    return
                }
            }
            if (document.isNotEmpty()){
                aux.add("document")
            }
            if (number.isNotEmpty()){
                aux.add("number")
            }
            status.postValue(aux as ArrayList<String>?)
            msg.value = "Se ha cambiado la informacion correctamente"
        } else {
            // si no digita alguno de los campos
            status.value?.clear()
            msg.value = "Debe digitar alguno de los campos"
            return
        }

    }

    private fun emailValidation(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}