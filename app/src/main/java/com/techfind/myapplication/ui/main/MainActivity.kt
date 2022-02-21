package com.techfind.myapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.techfind.myapplication.R
import com.techfind.myapplication.databinding.ActivityMainBinding
import com.techfind.myapplication.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var emailSend: String
    private lateinit var passwordSend: String

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Cerrando Práctica 1", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        var emailReceived: String? = ""
        var passwordReceived: String? = ""
        val credentials = intent.extras

        if (credentials != null){
            emailReceived = credentials.getString("email")
            passwordReceived = credentials.getString("password")
            emailSend = emailReceived.toString()
            passwordSend = passwordReceived.toString()
        }

        mainBinding.resultTextView.text = "Su correo electrónico es: " + emailReceived

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sign_out -> goToLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("email", emailSend)
        intent.putExtra("password", passwordSend)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}