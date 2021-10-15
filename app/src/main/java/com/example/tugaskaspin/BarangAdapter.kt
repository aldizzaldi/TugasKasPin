package com.example.tugaskaspin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaskaspin.room.Barang
import kotlinx.android.synthetic.main.adapter_main.view.*

class BarangAdapter (private val barangs: ArrayList<Barang>) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = barangs[position]
        holder.itemView.text_nama_barang.text = barang.nama
        holder.itemView.text_kode_barang.text = barang.kode
        holder.itemView.text_stok_barang.text= "Stok: " + barang.jumlah
    }

    override fun getItemCount() = barangs.size

    class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    fun setData(list: List<Barang>){
        barangs.clear()
        barangs.addAll(list)
        notifyDataSetChanged()
    }

}