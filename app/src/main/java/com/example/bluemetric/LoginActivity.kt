package com.example.bluemetric

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bluemetric.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString().trim()
            val password = binding.editPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                toast("Preencha todos os campos")
                return@setOnClickListener
            }

            val savedEmail = prefs.getString("email", null)
            val savedPass = prefs.getString("password", null)

            if (email == savedEmail && password == savedPass) {
                prefs.edit().putBoolean("logged", true).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                toast("E-mail ou senha incorretos")
            }
        }

        binding.GoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
