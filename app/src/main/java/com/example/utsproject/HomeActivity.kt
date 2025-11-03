package com.example.utsproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class HomeActivity : Activity() {

    private lateinit var btnViewCart: Button
    private lateinit var tvCartBadge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username") ?: "Nazwa"
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = "Halo $username,"

        // Cart button
        btnViewCart = findViewById(R.id.btnViewCart)
        tvCartBadge = findViewById(R.id.tvCartBadge)

        updateCartBadge()

        btnViewCart.setOnClickListener {
            if (CartManager.isEmpty()) {
                Toast.makeText(this, "Keranjang masih kosong", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PesananActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }

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
            addToCart("Nasi Goreng", "Rp 25.000")
        }

        menuMieAyam.setOnClickListener {
            addToCart("Mie Ayam", "Rp 20.000")
        }

        menuAyamGoreng.setOnClickListener {
            addToCart("Ayam Goreng + Nasi", "Rp 30.000")
        }

        menuSoto.setOnClickListener {
            addToCart("Soto Ayam", "Rp 22.000")
        }

        menuRendang.setOnClickListener {
            addToCart("Rendang + Nasi", "Rp 35.000")
        }

        menuGadoGado.setOnClickListener {
            addToCart("Gado-Gado", "Rp 18.000")
        }

        menuBakso.setOnClickListener {
            addToCart("Bakso Kuah", "Rp 23.000")
        }

        menuNasiUduk.setOnClickListener {
            addToCart("Nasi Uduk Komplit", "Rp 28.000")
        }

        menuSatePadang.setOnClickListener {
            addToCart("Sate Padang", "Rp 32.000")
        }

        menuNasiKuning.setOnClickListener {
            addToCart("Nasi Kuning Tumpeng", "Rp 27.000")
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

    override fun onResume() {
        super.onResume()
        updateCartBadge()
    }

    private fun addToCart(menuName: String, price: String) {
        CartManager.addItem(menuName, price)
        updateCartBadge()
        Toast.makeText(this, "$menuName ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
    }

    private fun updateCartBadge() {
        val totalItems = CartManager.getTotalItems()
        if (totalItems > 0) {
            tvCartBadge.visibility = View.VISIBLE
            tvCartBadge.text = totalItems.toString()
        } else {
            tvCartBadge.visibility = View.GONE
        }
    }
}