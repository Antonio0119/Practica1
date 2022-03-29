@file:Suppress("NAME_SHADOWING")

package com.techfind.myapplication.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private var statusO : Int = 0
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        auth = Firebase.auth

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        registerViewModel.msgDone.observe(this) { result ->
            Toast.makeText(this@RegisterActivity,result,Toast.LENGTH_SHORT).show()
        }
        registerViewModel.statusDone.observe(this@RegisterActivity){ result ->
            statusO = result
            Log.d("status",statusO.toString())
        }

        with(registerBinding) {
            registerButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repeatPassword = repeatPasswordEditText.text.toString()
                val document = documentEditText.text.toString()
                val number = numberEditText.text.toString()
                registerViewModel.validation(name,email,password,repeatPassword,document,number)
                if (statusO == 1){
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Register", "createUserWithEmail:success")
                                createUser(auth.currentUser?.uid, email,name,document.toInt(),password,number.toInt())
                                onBackPressed()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Register", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext, task.exception?.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                   // val intent =
                       // Intent(this@RegisterActivity, LoginActivity::class.java)
                   // startActivity(intent)
                    registerViewModel.saveUser(name,email,password, document.toInt(),number.toInt())
                }
            }
        }
    }

    private fun createUser(uid: String?, email: String, name: String, document: Int, password: String, cel_number: Int) {
        val db = Firebase.firestore
        val user = com.techfind.myapplication.server.User(uid = uid, email = email, name = name, document = document, password = password, cel_number = cel_number)
        uid?.let { uid->
            db.collection("users").document(uid).set(user)
                .addOnSuccessListener {
                    Toast.makeText(
                        baseContext,
                        "Usuario creado exitosamente",
                        Toast.LENGTH_SHORT).show()
                }
        }
    }

}