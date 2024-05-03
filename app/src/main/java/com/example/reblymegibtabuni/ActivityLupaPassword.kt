package com.example.reblymegibtabuni

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reblymegibtabuni.databinding.ActivityLupaPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityLupaPassword : AppCompatActivity() {
    private lateinit var binding: ActivityLupaPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupa_password)
        binding = ActivityLupaPasswordBinding.inflate(layoutInflater)


        binding.kirim.setOnClickListener {
            val email : String = binding.email.text.toString().trim()
            if (email.isEmpty()){
                binding.email.error = "Input Email"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.email.error = "Invalid email"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Cek email for reset password", Toast.LENGTH_SHORT).show()
                    Intent(this, ActivityMasuk::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }
                else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}