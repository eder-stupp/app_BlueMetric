package com.example.bluemetric

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bluemetric.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)

        binding.btnSalve.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val email = binding.editEmail.text.toString().trim()
            val password = binding.editPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || nome.isEmpty()) {
                toast("Preencha todos os campos")
                return@setOnClickListener
            }
            if (!email.contains("@")) {
                toast("E-mail inválido")
                return@setOnClickListener
            }
            if (password.length < 6) {
                toast("Senha deve ter no mínimo 6 caracteres")
                return@setOnClickListener
            }
            if (prefs.getString("email", null) == email) {
                toast("Email já existe")
                return@setOnClickListener
            }

            prefs.edit()
                .putString("nome", nome)
                .putString("email", email)
                .putString("password", password)
                .apply()

            toast("Cadastro realizado! Faça login.")
            finish()
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
