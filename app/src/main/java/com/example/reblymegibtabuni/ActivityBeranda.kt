package com.example.reblymegibtabuni

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reblymegibtabuni.databinding.ActivityBerandaBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ActivityBeranda : AppCompatActivity() {
    private lateinit var regeaRecyclerView: RecyclerView
    private lateinit var regeaList: List<Image>
    private lateinit var regeaAdapter: RegeaAdapter
    private lateinit var binding: ActivityBerandaBinding
    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        regeaRecyclerView = findViewById(R.id.img_item_photo)
        regeaRecyclerView.setHasFixedSize(true)
        regeaRecyclerView.layoutManager = LinearLayoutManager(this@ActivityBeranda)
        binding.myDataLoaderProgressBar.visibility = view.VISIBLE
        regeaList = ArrayList()
        regeaAdapter = RegeaAdapter(this@ActivityBeranda, regeaList)
        regeaRecyclerView.adapter = regeaAdapter

//        set firebase database
        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("regea")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener){
            override fun onCancelled(error: DatabaseError){
                Toast.makeText(this@ActivityBeranda, error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderProgressBar.visibility = View.INVISIBLE
            }
            override fun onDataChange(snapshot: DataSnapshot){
                regeaList.clear()
                for (teacherSnapshot in snapshot.children){
                    val upload = teacherSnapshot.getValue(Gambar::class.java)
                    upload!!.key = teacherSnapshot.key
                    regeaList.add(upload)
                }
                regeaAdapter.notifyDataSetChanged()
                binding.myDataLoaderProgressBar.visibility = View.GONE
            }
        }


        val recyclerView = findViewById<RecyclerView>(R.id.rv_Regea)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RegeaAdapter(this, regeaList){

            val intent = Intent(this, ActivityDetail::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                val intent = Intent(this, ActivityMasuk::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_profil -> {
                val intent = Intent(this, ActivityProfil::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_home -> {
                val intent = Intent(this, ActivityBeranda::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

