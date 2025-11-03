package com.example.utsproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class PesananActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesanan)

        val username = intent.getStringExtra("username") ?: "Nazwa"

        // Set username
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        // Container untuk cart items
        val containerCartItems = findViewById<LinearLayout>(R.id.containerCartItems)

        // Tampilkan semua item di keranjang
        val cartItems = CartManager.getItems()

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Keranjang kosong", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Add each cart item
        for (item in cartItems) {
            val itemView = createCartItemView(item)
            containerCartItems.addView(itemView)
        }

        // Hitung total
        val subtotal = CartManager.getTotalPrice()
        val deliveryFee = 5000
        val total = subtotal + deliveryFee

        // Set subtotal dan total
        val tvSubtotal = findViewById<TextView>(R.id.tvSubtotal)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        tvSubtotal.text = "Rp ${formatNumber(subtotal)}"
        tvTotal.text = "Rp ${formatNumber(total)}"

        // Tombol lanjutkan
        val btnLanjutkan = findViewById<Button>(R.id.btnLanjutkan)
        btnLanjutkan.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("subtotal", "Rp ${formatNumber(subtotal)}")
            intent.putExtra("total", "Rp ${formatNumber(total)}")
            startActivity(intent)
        }

        // Bottom Navigation
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        navHome.setOnClickListener {
            finish()
        }
    }

    private fun createCartItemView(item: CartManager.CartItem): LinearLayout {
        val itemLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 24
            }
            setBackgroundResource(R.drawable.card_background)
            setPadding(40, 40, 40, 40)
        }

        // Row untuk nama dan harga
        val rowLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val tvName = TextView(this).apply {
            text = item.menuName
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(resources.getColor(android.R.color.black))
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val tvPrice = TextView(this).apply {
            text = item.price
            textSize = 14f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(resources.getColor(android.R.color.holo_blue_dark))
        }

        rowLayout.addView(tvName)
        rowLayout.addView(tvPrice)

        // Row untuk quantity
        val qtyLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 16
            }
        }

        val tvQtyLabel = TextView(this).apply {
            text = "Jumlah:"
            textSize = 14f
            setTextColor(resources.getColor(android.R.color.darker_gray))
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val tvQty = TextView(this).apply {
            text = "x${item.quantity}"
            textSize = 14f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(resources.getColor(android.R.color.black))
        }

        qtyLayout.addView(tvQtyLabel)
        qtyLayout.addView(tvQty)

        itemLayout.addView(rowLayout)
        itemLayout.addView(qtyLayout)

        return itemLayout
    }

    private fun formatNumber(number: Int): String {
        return "%,d".format(number).replace(',', '.')
    }
}