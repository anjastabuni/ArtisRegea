package com.example.reblymegibtabuni

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val regea = intent.getParcelableExtra<Regea>(ActivityBeranda.INTENT_PARCELABLE)

        val imgRegea = findViewById<ImageView>(R.id.img_item_photo)
        val nameRegea = findViewById<TextView>(R.id.tv_item_name)
        val descRegea = findViewById<TextView>(R.id.tv_item_description)

        imgRegea.setImageResource(regea?.imgRegea!!)
        nameRegea.text = regea.nameRegea
        descRegea.text = regea.descRegea
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}