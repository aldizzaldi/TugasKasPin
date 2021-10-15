package com.example.tugaskaspin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaskaspin.room.Barang
import com.example.tugaskaspin.room.Constant
import kotlinx.android.synthetic.main.adapter_main.view.*

class BarangAdapter (private val barangs: ArrayList<Barang>, private val  listener: OnAdapterListener, private val identity: Int)
    : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

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
        holder.itemView.icon_edit.setOnClickListener {
            listener.onUpdate(barang)
        }
        holder.itemView.icon_delete.setOnClickListener {
            listener.onDelete(barang)
        }
        if (identity == Constant.TRANSAKSI){
            holder.itemView.icon_delete.visibility = View.GONE
            holder.itemView.icon_edit.visibility = View.GONE
            holder.itemView.text_stok_barang.visibility = View.GONE
            holder.itemView.btn_tambah_keranjang.visibility = View.VISIBLE
            holder.itemView.btn_tambah_keranjang.setOnClickListener {
                listener.onKurangiStok(barang)
            }
        }
    }

    override fun getItemCount() = barangs.size

    class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    fun setData(list: List<Barang>){
        barangs.clear()
        barangs.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onUpdate(barang: Barang)
        fun onDelete(barang: Barang)
        fun onKurangiStok(barang: Barang)
    }

}