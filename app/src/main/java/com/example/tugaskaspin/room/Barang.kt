package com.example.tugaskaspin.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val kode: String,
    val nama: String,
    val jumlah: Int
)
