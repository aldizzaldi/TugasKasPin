package com.example.tugaskaspin.room

import androidx.room.*

@Dao
interface BarangDao {

    @Insert
    suspend fun addBarang(barang: Barang)

    @Update
    suspend fun updateBarang(barang: Barang)

    @Delete
    suspend fun deleteBarang(barang: Barang)

    @Query("SELECT * FROM barang")
    suspend fun getBarangs(): List<Barang>

    @Query("SELECT * FROM barang WHERE id=:barang_id")
    suspend fun getBarang(barang_id: Int): List<Barang>
}