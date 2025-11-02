package com.example.utsproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class PesananActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesanan)

        val username = intent.getStringExtra("username") ?: "Nazwa"
        val menuName = intent.getStringExtra("menuName") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        // Hitung total (harga menu + ongkir)
        val menuPrice = price.replace("Rp ", "").replace(".", "").toIntOrNull() ?: 0
        val deliveryFee = 5000
        val total = menuPrice + deliveryFee

        // Set username
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        // Set detail pesanan
        val tvMenuName = findViewById<TextView>(R.id.tvMenuName)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        tvMenuName.text = menuName
        tvPrice.text = price
        tvTotal.text = "Rp ${formatNumber(total)}"

        // Tombol lanjutkan
        val btnLanjutkan = findViewById<Button>(R.id.btnLanjutkan)
        btnLanjutkan.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("menuName", menuName)
            intent.putExtra("price", price)
            intent.putExtra("total", "Rp ${formatNumber(total)}")
            startActivity(intent)
        }

        // Bottom Navigation
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun formatNumber(number: Int): String {
        return "%,d".format(number).replace(',', '.')
    }
}