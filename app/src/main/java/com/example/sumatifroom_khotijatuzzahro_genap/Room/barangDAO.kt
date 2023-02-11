package com.example.sumatifroom_khotijatuzzahro_genap.Room

import androidx.room.*


@Dao
interface barangDAO {
    @Insert
     fun addtbBarang (tbBarang: tb_barang)

     @Update
     fun UpdatetbBarang (tbBarang: tb_barang)

     @Delete
     fun DeletetbBarang (tbBarang: tb_barang)

     @Query("SELECT * FROM tb_barang")
     fun gettbBarang():List<tb_barang>

     @Query("SELECT * FROM tb_barang WHERE id=:brng_id")
      fun gettbBarangs(brng_id:Int):List<tb_barang>
}