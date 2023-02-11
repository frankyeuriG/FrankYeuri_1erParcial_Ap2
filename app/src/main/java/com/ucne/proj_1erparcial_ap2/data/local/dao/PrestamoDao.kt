package com.ucne.proj_1erparcial_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamoEntity: PrestamoEntity)

    @Query("""
        SELECT * 
        FROM Prestamos
        ORDER BY prestamoId desc
    """)
    fun getlist(): Flow<List<PrestamoEntity>>
}