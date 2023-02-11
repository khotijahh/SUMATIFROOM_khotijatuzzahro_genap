package com.example.sumatifroom_khotijatuzzahro_genap

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_khotijatuzzahro_genap.Room.Codepelita
import com.example.sumatifroom_khotijatuzzahro_genap.Room.Constant
import com.example.sumatifroom_khotijatuzzahro_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

   val db by lazy { Codepelita(this) }
   private lateinit var BarangAdapter: barangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setUpRecyclerview()
    }

    override fun onStart(){
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val barang = db.BarangDAO().gettbBarang()
            Log.d("MainActivity","dbResponse: $barang")
            withContext(Dispatchers.Main){
                BarangAdapter.setData(barang)
            }
        }
    }

    private fun halEdit(){
        btnInput.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(tbBarang_id: Int ,intentType: Int){
        startActivity(
            Intent(applicationContext,editActivity::class.java)
                .putExtra("intent_id",tbBarang_id)
                .putExtra("intent_type",intentType)
        )
    }

    fun setUpRecyclerview(){
        BarangAdapter = barangAdapter(arrayListOf(),object :barangAdapter.onAdapterListener{
            override fun onClik(tbBarang: tb_barang) {
                  intentEdit(tbBarang.id,Constant.TYPE_READ)
            }

            override fun onUpdate(tbBarang: tb_barang) {
                intentEdit(tbBarang.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBarang: tb_barang) {
               deleteDialog(tbBarang)
            }
        })
            rvNama.apply{
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = BarangAdapter
            }
        }

    private fun deleteDialog(tbBarang: tb_barang){
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setTitle("konfirmasi")
            setMessage("yakin hapus ${tbBarang.nama_barang}?")
            setNegativeButton("batal"){ dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("delete"){ dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.BarangDAO().DeletetbBarang(tbBarang)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}
