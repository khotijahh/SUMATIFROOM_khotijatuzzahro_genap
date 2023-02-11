package com.example.sumatifroom_khotijatuzzahro_genap.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tb_barang(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nama_barang : String,
    val harga : Int,
    val QTY : Int
)