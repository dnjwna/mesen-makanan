package com.example.utsproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username") ?: "Nazwa"
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        // Menu Items
        val menuNasiGoreng = findViewById<LinearLayout>(R.id.menuNasiGoreng)
        val menuMieAyam = findViewById<LinearLayout>(R.id.menuMieAyam)
        val menuAyamGoreng = findViewById<LinearLayout>(R.id.menuAyamGoreng)
        val menuSoto = findViewById<LinearLayout>(R.id.menuSoto)
        val menuRendang = findViewById<LinearLayout>(R.id.menuRendang)
        val menuGadoGado = findViewById<LinearLayout>(R.id.menuGadoGado)
        val menuBakso = findViewById<LinearLayout>(R.id.menuBakso)
        val menuNasiUduk = findViewById<LinearLayout>(R.id.menuNasiUduk)
        val menuSatePadang = findViewById<LinearLayout>(R.id.menuSatePadang)
        val menuNasiKuning = findViewById<LinearLayout>(R.id.menuNasiKuning)

        menuNasiGoreng.setOnClickListener {
            selectMenu(username, "Nasi Goreng", "Rp 25.000")
        }

        menuMieAyam.setOnClickListener {
            selectMenu(username, "Mie Ayam", "Rp 20.000")
        }

        menuAyamGoreng.setOnClickListener {
            selectMenu(username, "Ayam Goreng + Nasi", "Rp 30.000")
        }

        menuSoto.setOnClickListener {
            selectMenu(username, "Soto Ayam", "Rp 22.000")
        }

        menuRendang.setOnClickListener {
            selectMenu(username, "Rendang + Nasi", "Rp 35.000")
        }

        menuGadoGado.setOnClickListener {
            selectMenu(username, "Gado-Gado", "Rp 18.000")
        }

        menuBakso.setOnClickListener {
            selectMenu(username, "Bakso Kuah", "Rp 23.000")
        }

        menuNasiUduk.setOnClickListener {
            selectMenu(username, "Nasi Uduk Komplit", "Rp 28.000")
        }

        menuSatePadang.setOnClickListener {
            selectMenu(username, "Sate Padang", "Rp 32.000")
        }

        menuNasiKuning.setOnClickListener {
            selectMenu(username, "Nasi Kuning Tumpeng", "Rp 27.000")
        }

        // Bottom Navigation
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navOrder = findViewById<LinearLayout>(R.id.navOrder)
        val navProfile = findViewById<LinearLayout>(R.id.navProfile)

        navHome.setOnClickListener {
            // Already on home
        }

        navOrder.setOnClickListener {
            Toast.makeText(this, "Order menu", Toast.LENGTH_SHORT).show()
        }

        navProfile.setOnClickListener {
            Toast.makeText(this, "Profile menu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectMenu(username: String, menuName: String, price: String) {
        val intent = Intent(this, PesananActivity::class.java)
        intent.putExtra("username", username)
        intent.putExtra("menuName", menuName)
        intent.putExtra("price", price)
        startActivity(intent)
    }
}