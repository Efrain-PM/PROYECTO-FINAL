package com.example.proyectofinal.data.Repository

import com.example.proyectofinal.data.dao.MovementDao
import com.example.proyectofinal.data.model.Movement
import kotlinx.coroutines.flow.Flow

class MovementRepository(private val movementDao: MovementDao) {

    val allMovements: Flow<List<Movement>> = movementDao.getAllMovements()

    suspend fun insert(movement: Movement) {
        movementDao.insertMovement(movement)
    }

    suspend fun delete(movement: Movement) {
        movementDao.deleteMovement(movement)
    }
}