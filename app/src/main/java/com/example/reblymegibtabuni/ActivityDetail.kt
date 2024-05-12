package com.example.reblymegibtabuni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reblymegibtabuni.databinding.ActivityDetailBinding

class ActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intss = intent
        var nameT = intss.getStringExtra("NAMET")
        var desT = intss.getStringExtra("DESCRIPT")
        var imgT = intss.getStringExtra("IMGURI")

        binding.itemname.text = nameT
        binding.itemdescription.text = desT
        binding.imgitemphoto.loadImage(imgT)

    }
}