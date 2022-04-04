package com.techfind.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.ui.register.RegisterActivity
import com.techfind.myapplication.databinding.ActivityLoginBinding
import com.techfind.myapplication.ui.bottom.BottomActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        auth = Firebase.auth

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginBinding.notHaveAccountTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        with(loginBinding){
            loginButton.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                Log.d("2","what da hell its going on")
                GlobalScope.launch (Dispatchers.IO) {
                    loginUser(email, password)
                }
            }
        }

    }

    suspend fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                Log.d("2","what its going on")
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@LoginActivity, BottomActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Usuario o contraseña incorrectos",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}