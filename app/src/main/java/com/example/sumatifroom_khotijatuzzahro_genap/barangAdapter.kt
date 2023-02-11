package com.example.sumatifroom_khotijatuzzahro_genap


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_khotijatuzzahro_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_barang_adapter.view.*
import kotlinx.android.synthetic.main.activity_edit.view.*

class barangAdapter (private val barang:ArrayList<tb_barang>,private val listener: onAdapterListener)
    :RecyclerView.Adapter<barangAdapter.barangViewHolder>(){

    class barangViewHolder (val view: View):RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): barangViewHolder {
        return barangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_barang_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: barangViewHolder, position: Int) {
        val brng = barang[position]
        holder.view.text_Id.text = brng.id.toString()
        holder.view.text_nama.text = brng.nama_barang
        holder.view.CTview.setOnClickListener{
            listener.onClik(brng)
        }
        holder.view.ic_edit.setOnClickListener{
            listener.onUpdate(brng)
        }
        holder.view.ic_delete.setOnClickListener{
            listener.onDelete(brng)
        }
    }

    override fun getItemCount() = barang.size

    fun setData(list: List<tb_barang>){
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }

    interface onAdapterListener{
        fun onClik(tbBarang: tb_barang)
        fun onUpdate(tbBarang: tb_barang)
        fun onDelete(tbBarang: tb_barang)
    }
}