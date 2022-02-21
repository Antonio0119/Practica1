package com.techfind.myapplication.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.techfind.myapplication.databinding.ActivityRegisterBinding
import com.techfind.myapplication.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        with(registerBinding) {
            registerButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repeatPassword = repeatPasswordEditText.text.toString()
                val document = documentEditText.text.toString()
                val number = numberEditText.text.toString()
                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
                    && repeatPassword.isNotEmpty() && document.isNotEmpty() && number.isNotEmpty()
                ) {
                    if (password.length >= 6) {
                        // Si las contraseñas son iguales se verifica la validación del email
                        if (password == repeatPassword) {

                            // Si la validación es true se indica un registro exitoso y se realiza el intent a Login
                            if (this@RegisterActivity.emailValidation(email)) {
                                Toast.makeText(
                                    applicationContext,
                                    "Registro exitoso",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent =
                                    Intent(this@RegisterActivity, LoginActivity::class.java)
                                intent.putExtra("email", email)
                                intent.putExtra("password", password)
                                startActivity(intent)

                            } else
                                Toast.makeText(
                                    applicationContext,
                                    "Email Inválido",
                                    Toast.LENGTH_SHORT
                                ).show()
                        } else
                            Toast.makeText(
                                applicationContext,
                                "Las contraseñas no son iguales",
                                Toast.LENGTH_SHORT
                            ).show()
                    } else
                        Toast.makeText(
                            applicationContext,
                            "La contraseña debe tener mínimo 6 dígitos",
                            Toast.LENGTH_SHORT
                        ).show()

                } else
                    Toast.makeText(
                        applicationContext,
                        "Debe digitar todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    // Función para validar el email
    private fun emailValidation(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}