package com.example.sumatifroom_khotijatuzzahro_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_khotijatuzzahro_genap.Room.Codepelita
import com.example.sumatifroom_khotijatuzzahro_genap.Room.Constant
import com.example.sumatifroom_khotijatuzzahro_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class editActivity : AppCompatActivity() {

     val db by lazy { Codepelita(this) }
    private var tbBarang_id : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        simpandata()
        setupView()
        tbBarang_id = intent.getIntExtra("intent_id",tbBarang_id)
        Toast.makeText(this,tbBarang_id.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intenType= intent.getIntExtra("intent_type",0)
        when(intenType){
            Constant.TYPE_CREATE ->{
            btnUpdate.visibility = View.GONE
            }

            Constant.TYPE_READ -> {
                btnSave.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                edit_Id.visibility = View.GONE
                tampilanBarang()
            }
            Constant.TYPE_UPDATE ->{
                btnSave.visibility = View.GONE
                edit_Id.visibility = View.GONE
                tampilanBarang()
            }
        }
    }

    fun simpandata() {
        btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.BarangDAO().addtbBarang(
                    tb_barang(edit_Id.text.toString().toInt(),
                                edit_namaBrng.text.toString(),
                                edit_harga.text.toString().toInt(),
                                edit_qty.text.toString().toInt())
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.BarangDAO().UpdatetbBarang(
                    tb_barang(tbBarang_id,
                                edit_namaBrng.text.toString(),
                                edit_harga.text.toString().toInt(),
                                edit_qty.text.toString().toInt())
                )
                finish()
            }
        }
    }

    fun tampilanBarang(){
        tbBarang_id = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val tbBarang = db.BarangDAO().gettbBarangs(tbBarang_id)[0]
            val dataId: String = tbBarang.id.toString()
            val dataQty: String = tbBarang.QTY.toString()
            val dataHarga: String = tbBarang.harga.toString()
            edit_Id.setText(dataId)
            edit_harga.setText(dataHarga)
            edit_namaBrng.setText(tbBarang.nama_barang)
            edit_qty.setText(dataQty)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}