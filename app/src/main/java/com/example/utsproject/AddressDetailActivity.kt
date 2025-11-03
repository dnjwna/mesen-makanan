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
        val subtotal = intent.getStringExtra("subtotal") ?: ""
        val total = intent.getStringExtra("total") ?: ""

        // Set username di TextView
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        // Build order summary
        val cartItems = CartManager.getItems()
        val orderSummary = StringBuilder()

        orderSummary.append("Konfirmasi Pesanan\n\n")
        orderSummary.append("Menu yang dipesan:\n")

        for (item in cartItems) {
            orderSummary.append("• ${item.menuName} x${item.quantity} - ${item.price}\n")
        }

        orderSummary.append("\nSubtotal: $subtotal\n")
        orderSummary.append("Biaya Pengiriman: Rp 5.000\n")
        orderSummary.append("Total: $total\n\n")
        orderSummary.append("Dikirim ke:\n$alamat\n\n")
        orderSummary.append("Atas nama: $nama")

        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        tvInfo.text = orderSummary.toString()

        val btnKirim = findViewById<Button>(R.id.btnKirim)
        btnKirim.setOnClickListener {
            Toast.makeText(this, "Pesanan berhasil! Total: $total", Toast.LENGTH_LONG).show()
            CartManager.clear() // Hapus semua item di keranjang
            finish()
        }
    }
}