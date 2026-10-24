package com.example.proyectofinal.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.Database.AppDatabase
import com.example.proyectofinal.data.model.Movement
import com.example.proyectofinal.data.model.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val productDao = AppDatabase.getDatabase(application).productDao()
    private val movementDao = AppDatabase.getDatabase(application).movementDao()

    val quickProducts: StateFlow<List<Product>> = productDao.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addQuickProduct(nameStr: String, priceStr: String, onSuccess: () -> Unit) {
        val price = priceStr.toFloatOrNull() ?: 0f
        if (nameStr.isNotBlank() && price > 0f) {
            viewModelScope.launch {
                val newProduct = Product(
                    name = nameStr,
                    price = price
                )
                productDao.insertProduct(newProduct)
                onSuccess()
            }
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            productDao.deleteProduct(product)
        }
    }

    fun onProductClicked(product: Product, onShowToast: (String) -> Unit) {
        viewModelScope.launch {
            val currentDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
            val newMovement = Movement(
                title = product.name,
                amount = product.price,
                type = "SUMA",
                date = currentDate
            )
            movementDao.insertMovement(newMovement)
            onShowToast("¡Se registró '${product.name}' por $${product.price}!")
        }
    }
}