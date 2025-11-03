package com.example.utsproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AddressActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val username = intent.getStringExtra("username") ?: "Nazwa"
        val subtotal = intent.getStringExtra("subtotal") ?: ""
        val total = intent.getStringExtra("total") ?: ""

        // Set username di TextView
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        val etNama = findViewById<EditText>(R.id.etNama)
        val etAlamatLengkap = findViewById<EditText>(R.id.etAlamatLengkap)
        val btnOrderSekarang = findViewById<Button>(R.id.btnOrderSekarang)

        btnOrderSekarang.setOnClickListener {
            val nama = etNama.text.toString()
            val alamat = etAlamatLengkap.text.toString()

            if (nama.isNotEmpty() && alamat.isNotEmpty()) {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("nama", nama)
                intent.putExtra("alamat", alamat)
                intent.putExtra("subtotal", subtotal)
                intent.putExtra("total", total)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}