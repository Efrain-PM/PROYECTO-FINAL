package com.example.proyectofinal.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.Database.AppDatabase
import com.example.proyectofinal.data.Repository.MovementRepository
import com.example.proyectofinal.data.model.Movement
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MovementsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovementRepository

    init {
        val movementDao = AppDatabase.getDatabase(application).movementDao()
        repository = MovementRepository(movementDao)
    }

    val movements: StateFlow<List<Movement>> = repository.allMovements
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val totalBalance: Float
        get() {
            var balance = 12450.0f
            for (m in movements.value) {
                if (m.type == "SUMA") {
                    balance += m.amount
                } else if (m.type == "RESTA") {
                    balance -= m.amount
                }
            }
            return balance
        }

    //YA FUNCIONAAAAA----------------------------
    fun addMovement(title: String, amountStr: String, type: String, onSuccess: () -> Unit) {
        val amount = amountStr.toFloatOrNull() ?: 0f

        // Validaciones de negocio en el ViewModel
        if (title.isNotBlank() && amount > 0f) {
            viewModelScope.launch {
                val currentDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
                val newMovement = Movement(
                    title = title,
                    amount = amount,
                    type = type, // suma o resta
                    date = currentDate
                )
                repository.insert(newMovement)
                onSuccess()
            }
        }
    }

    fun deleteMovement(movement: Movement) {
        viewModelScope.launch {
            repository.delete(movement)
        }
    }
}