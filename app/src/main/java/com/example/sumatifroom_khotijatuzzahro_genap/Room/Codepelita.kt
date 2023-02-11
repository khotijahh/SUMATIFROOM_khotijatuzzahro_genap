package com.example.sumatifroom_khotijatuzzahro_genap.Room

import android.content.Context
import androidx.core.text.buildSpannedString
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(
    entities = [tb_barang::class],
    version= 1)

    abstract class Codepelita: RoomDatabase(){
        abstract fun BarangDAO(): barangDAO

        companion object{
            @Volatile private var instance:Codepelita? = null
            private val Lock = Any()

            operator fun invoke(context: Context) = instance?: synchronized(Lock) {
                instance?: buildDatabase(context).also {
                    instance= it
                }
            }

            private fun buildDatabase(context: Context)= Room.databaseBuilder(
                context.applicationContext,
                Codepelita::class.java,
                 "20,5395.Codepelita"

            ).build()
        }
    }