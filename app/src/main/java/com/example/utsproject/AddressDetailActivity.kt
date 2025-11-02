package com.example.utsproject

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class AddressDetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_detail)

        val username = intent.getStringExtra("username") ?: "Nazwa"
        val nama = intent.getStringExtra("nama") ?: ""
        val alamat = intent.getStringExtra("alamat") ?: ""
        val menuName = intent.getStringExtra("menuName") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        // Set username di TextView
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        tvInfo.text = """
            Konfirmasi Pesanan
            
            Menu: $menuName
            Harga: $price
            
            Dikirim ke:
            $alamat
            
            Atas nama: $nama
        """.trimIndent()

        val btnKirim = findViewById<Button>(R.id.btnKirim)
        btnKirim.setOnClickListener {
            Toast.makeText(this, "Pesanan $menuName berhasil dipesan!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}