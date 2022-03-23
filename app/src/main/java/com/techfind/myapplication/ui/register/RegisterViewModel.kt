package com.techfind.myapplication.ui.register

import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.local.User
import com.techfind.myapplication.repository.TechfindRepository
import com.techfind.myapplication.ui.bottom.BottomActivity
import com.techfind.myapplication.ui.login.LoginActivity
import java.sql.Types.NULL

class RegisterViewModel: ViewModel() {
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg
    private val status: MutableLiveData<Int> = MutableLiveData()
    val statusDone: LiveData<Int> = status
    private val userRepository = TechfindRepository()
    private lateinit var auth: FirebaseAuth


    fun emailValidation(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun Validation(name: String, email: String, password: String, repeatPassword: String, document: String, number: String)
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

                        msg.value = "Registro exitoso"
                        Log.d("myTag", "Reg ex");
                        status.value = 1

                    } else {
                        //Si el email es invalido
                        msg.value = "Email Inválido"
                        Log.d("myTag", "Em inv");
                    }
                } else {
                    //Si las contrasenas no son iguales
                    msg.value = "Las contraseñas no son iguales"
                    Log.d("myTag", "cont no");
                }
            } else {
                // si la contrasena no tiene minimo 6 digitos
                msg.value = "La contraseña debe tener mínimo 6 dígitos"
                Log.d("myTag", "cont min");
            }
        } else {
            // si no digita todos los campos
            msg.value = "Debe digitar todos los campos"
            Log.d("myTag","digita tod campos")
        }
    }

    fun saveUser(
        name: String,
        email: String,
        password: String,
        document: Int,
        number: Int,
    ) {
        userRepository.newUser(name,email,password,document,number)
        Log.d("newuser",name)
    }

   /* private fun createUser(user_id: String?, email: String) {
        val db = Firebase.firestore
        val user = User(user_id = user_id, email = email, role = Role.VENDEDOR)
        user_id?.let { user_id->
            db.collection("users").document(user_id).set(user)
                .addOnSuccessListener {
                    Toast.makeText(baseContext,"Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                }
        }
    }*/

}


