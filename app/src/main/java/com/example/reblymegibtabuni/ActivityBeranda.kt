package com.example.reblymegibtabuni

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityBeranda : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beranda)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.itemId)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    val intentMasuk = Intent(this, ActivityMasuk::class.java)
                    startActivity(intentMasuk)
                    true
                }
                R.id.menu_profil -> {
                    val intentProfil = Intent(this, ActivityProfil::class.java)
                    startActivity(intentProfil)
                    true
                }
                R.id.menu_home -> {
                    val intentBeranda = Intent(this, ActivityBeranda::class.java)
                    startActivity(intentBeranda)
                    true
                }
                else -> false
            }
        }



        val regeaList = listOf(
            Regea(
                R.drawable.bobmarley,
                "Robert Nesta Marley",
                "Lagu-lagu hits : No Woman No Cry, Redemption Song, Buffalo Soldier, One Love"
            ),
            Regea(
                R.drawable.alton,
                "Alton Nehemiah Ellis",
                "Lagu-lagu hits : Girl I've Got a Date, Dance Crasher, I'm Still in Love with You, Why Birds Follow Spring"
            ),
            Regea(
                R.drawable.dennis,
                "Dennis Emmanuel Brown",
                "Lagu-lagu hits : Money in My Pocket, Revolution, Here I Come, Westbound Train"
            ),
            Regea(
                R.drawable.desmond,
                "Desmond Adolphus Dekker",
                "Lagu-lagu hits : King of Ska, Rudy Got Soul, Sing a Little Song, Rude Boy Train"
            ),
            Regea(
                R.drawable.gregory,
                "Gregory Anthony Isaacs",
                "Lagu-lagu hits : Night Nurse, My Number One, Cool Down the Pace, Rumours"
            ),
            Regea(
                R.drawable.jimmy,
                "James Chambers",
                "Lagu-lagu hits : I Can See Clearly Now, Many Rivers to Cross, The Harder They Come, Reggae Night"
            ),
            Regea(
                R.drawable.michael,
                "Michael Rose",
                "Lagu-lagu hits : Sponji Reggae, I Love King Selassie, Illegal, Shoot Out"
            ),
            Regea(
                R.drawable.peter,
                "Winston Hubert McIntosh",
                "Lagu-lagu hits : Waiting in Vain, Legalize It, Would You Be Loved, Jamming"
            ),
            Regea(
                R.drawable.toots,
                "Frederick Nathaniel Hibbert",
                "Lagu-lagu hits : Pressure Drop, Monkey Man, Louie Louie, Funky Kingston"
            )

        )

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

