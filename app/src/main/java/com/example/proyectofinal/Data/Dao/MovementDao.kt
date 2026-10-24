package com.example.proyectofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectofinal.data.model.Movement
import kotlinx.coroutines.flow.Flow

@Dao
interface MovementDao {

    @Query("SELECT * FROM movements ORDER BY id DESC")
    fun getAllMovements(): Flow<List<Movement>>

    @Insert
    suspend fun insertMovement(movement: Movement)

    @Delete
    suspend fun deleteMovement(movement: Movement)
}