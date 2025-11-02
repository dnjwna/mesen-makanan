package com.example.utsproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class LocationActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val username = intent.getStringExtra("username") ?: "Nazwa"
        val menuName = intent.getStringExtra("menuName") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        val btnKirim = findViewById<Button>(R.id.btnKirim)
        btnKirim.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("menuName", menuName)
            intent.putExtra("price", price)
            startActivity(intent)
        }

        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navOrder = findViewById<LinearLayout>(R.id.navOrder)
        val navProfile = findViewById<LinearLayout>(R.id.navProfile)

        navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}