package com.example.reblymegibtabuni

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reblymegibtabuni.databinding.ActivityBuatAkunBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityBuatAkun : AppCompatActivity() {

    private lateinit var binding: ActivityBuatAkunBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuatAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonRegister.setOnClickListener {
            val email : String = binding.Email.text.toString().trim()
            val password : String = binding.password.text.toString().trim()
            val confirPassword : String = binding.confirmPassword.text.toString().trim()


            if (email.isEmpty()){
                binding.Email.error = "Input Email"
                binding.Email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.Email.error = "Invalid email"
                binding.Email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                binding.password.error = "password must be more than 6 characters"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            if (password != confirPassword){
                binding.confirmPassword.error = "password must be match"
                binding.confirmPassword.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }

        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this, ActivityBeranda::class.java))
        }


    }

    private fun registerUser(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if (it.isSuccessful){
                Intent(this, ActivityBeranda::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
           else{
               Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
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