package com.techfind.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.techfind.myapplication.ui.main.MainActivity
import com.techfind.myapplication.ui.register.RegisterActivity
import com.techfind.myapplication.databinding.ActivityLoginBinding
import com.techfind.myapplication.ui.bottom.BottomActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private var statusO : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginBinding.notHaveAccountTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginViewModel.statusDone.observe(this@LoginActivity){ result ->
            statusO = result
        }

        loginViewModel.msgDone.observe(this) { result ->
            Toast.makeText(this@LoginActivity,result,Toast.LENGTH_SHORT).show()
        }

        with(loginBinding){
            loginButton.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                loginViewModel.Validate(email,password)
                if (statusO == 1){
                    val intent =
                        Intent(this@LoginActivity, BottomActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}