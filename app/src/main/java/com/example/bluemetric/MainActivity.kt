package com.example.bluemetric

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bluemetric.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val nome = prefs.getString("nome", "-")
        val email = prefs.getString("email", "-")

        binding.tvWelcome.text = "$nome\n$email"

        binding.btnLogout.setOnClickListener {
            prefs.edit().putBoolean("logged", false).apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
