package com.example.reblymegibtabuni

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reblymegibtabuni.databinding.ActivityMasukBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityMasuk : AppCompatActivity() {

    private lateinit var binding: ActivityMasukBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


       binding.btnLogin.setOnClickListener {
           val email : String = binding.email.text.toString().trim()
           val password : String = binding.password.text.toString().trim()

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
           if (password.isEmpty() || password.length<6){
               binding.password.error = "password more than 6 characters"
               binding.password.requestFocus()
               return@setOnClickListener
           }
           loginUser(email, password)
       }

        binding.textViewRegister.setOnClickListener {
            startActivity(Intent(this, ActivityBuatAkun::class.java))
        }

        binding.textViewForgotPassword.setOnClickListener {
            Intent(this, ActivityLupaPassword::class.java).also {
             startActivity(it)
            }
        }
    }

    private fun loginUser(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, ActivityBeranda::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null){
            Intent(this, ActivityBeranda::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}