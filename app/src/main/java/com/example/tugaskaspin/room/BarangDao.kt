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

    @Query("UPDATE barang SET jumlah = jumlah - 1 WHERE id=:barang_id")
    suspend fun kurangiStok(barang_id: Int)

    @Query("UPDATE barang SET jumlah = jumlah + 1 WHERE id=:barang_id")
    suspend fun tambahiStok(barang_id: Int)


}