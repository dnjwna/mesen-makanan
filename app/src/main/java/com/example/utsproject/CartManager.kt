package com.example.utsproject

object CartManager {
    data class CartItem(
        val menuName: String,
        val price: String,
        var quantity: Int = 1
    )

    private val cartItems = mutableListOf<CartItem>()

    fun addItem(menuName: String, price: String) {
        val existingItem = cartItems.find { it.menuName == menuName }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(menuName, price, 1))
        }
    }

    fun removeItem(menuName: String) {
        cartItems.removeAll { it.menuName == menuName }
    }

    fun updateQuantity(menuName: String, quantity: Int) {
        val item = cartItems.find { it.menuName == menuName }
        if (item != null) {
            if (quantity > 0) {
                item.quantity = quantity
            } else {
                removeItem(menuName)
            }
        }
    }

    fun getItems(): List<CartItem> {
        return cartItems.toList()
    }

    fun getTotalItems(): Int {
        return cartItems.sumOf { it.quantity }
    }

    fun getTotalPrice(): Int {
        return cartItems.sumOf {
            val price = it.price.replace("Rp ", "").replace(".", "").toIntOrNull() ?: 0
            price * it.quantity
        }
    }

    fun clear() {
        cartItems.clear()
    }

    fun isEmpty(): Boolean {
        return cartItems.isEmpty()
    }
}