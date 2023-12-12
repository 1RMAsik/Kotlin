package com.example.lab16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(parseProducts(getString(R.string.product_data)))
        recyclerView.adapter = adapter

        val showCardButton: CardView = findViewById(R.id.showCardButton)
        showCardButton.setOnClickListener {
            // Выберем первый товар из списка для отображения карточки
            val product = adapter.getProduct(0)

            // Показать диалоговое окно с информацией о товаре
            showProductCardDialog(product)
        }
    }

    private fun showProductCardDialog(product: Product) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.card_item, null)

        // Инициализировать View в диалоге
        val nameTextView: TextView = dialogView.findViewById(R.id.cardNameTextView)
        val quantityTextView: TextView = dialogView.findViewById(R.id.cardQuantityTextView)
        val priceTextView: TextView = dialogView.findViewById(R.id.cardPriceTextView)

        // Установить значения в View
        nameTextView.text = "Name: ${product.name}"
        quantityTextView.text = "Quantity: ${product.quantity}"
        priceTextView.text = "Price: ${product.price}"

        dialogBuilder.setView(dialogView)
        dialogBuilder.setTitle("Product Details")
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun parseProducts(productData: String): List<Product> {
        val products = mutableListOf<Product>()
        val lines = productData.split("\n")
        for (line in lines){
            val parts = line.split("|").map { it.trim() }
            if (parts.size == 3) {
                val name = parts[0]
                val quantity = parts[1]
                val price = parts[2]
                products.add(Product(name, quantity, price))
            }
        }
        return products
    }
}