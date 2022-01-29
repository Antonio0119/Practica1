package com.techfind.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.techfind.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        with(registerBinding) {
            registerButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repeatPassword = repeatPasswordEditText.text.toString()

                if (password.length >= 6){

                    // Si las contraseñas son iguales se verifica la validación del email
                    if (password == repeatPassword){

                        // Si la validación es true se indica un registro exitoso y se realiza el intent a Login
                        if (this@RegisterActivity.emailValidation(email)){
                            Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            startActivity(intent)

                        } else
                            Toast.makeText(applicationContext, "Email Inválido", Toast.LENGTH_SHORT).show()
                    } else
                        Toast.makeText(applicationContext, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(applicationContext, "La contraseña debe tener mínimo 6 dígitos", Toast.LENGTH_SHORT).show()


                /*
                if (password == repeatPassword && this@RegisterActivity.emailValidation(email)){
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    startActivity(intent)

                } else
                    Toast.makeText(applicationContext, "Email o contraseñas inválidas", Toast.LENGTH_SHORT).show()*/
            }
        }
    }

    // Función para validar el email
    private fun emailValidation(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}