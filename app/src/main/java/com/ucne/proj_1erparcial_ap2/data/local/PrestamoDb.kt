package com.ucne.proj_1erparcial_ap2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.proj_1erparcial_ap2.data.local.dao.PrestamoDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity

@Database(
    entities = [PrestamoEntity::class],
    version = 1
)
abstract class PrestamoDb: RoomDatabase(){
    abstract val prestamoDao: PrestamoDao
}