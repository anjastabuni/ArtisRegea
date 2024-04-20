package com.example.reblymegibtabuni

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.reblymegibtabuni.databinding.ActivityMasukBinding

class ActivityMasuk : AppCompatActivity() {

    private lateinit var binding: ActivityMasukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_masuk)


                binding = ActivityMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.btnLogin.setOnClickListener {
           val intentHome = Intent(this, ActivityBeranda::class.java)
           startActivity(intentHome)
       }

        binding.textViewForgotPassword.setOnClickListener {
            val intentForgetPassword = Intent(this, ActivityLupaPassword::class.java)
            startActivity(intentForgetPassword)
        }

        binding.textViewRegister.setOnClickListener {
            val intentRegister = Intent(this, ActivityBuatAkun::class.java)
            startActivity(intentRegister)
        }
    }
}