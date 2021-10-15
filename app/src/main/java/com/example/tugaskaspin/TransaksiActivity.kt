package com.example.tugaskaspin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaskaspin.room.Barang
import com.example.tugaskaspin.room.BarangDB
import com.example.tugaskaspin.room.Constant
import kotlinx.android.synthetic.main.activity_add_barang.*
import kotlinx.android.synthetic.main.activity_barang.*
import kotlinx.android.synthetic.main.activity_transaksi.*
import kotlinx.android.synthetic.main.adapter_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransaksiActivity : AppCompatActivity() {

    val db by lazy { BarangDB(this) }
    private var barangId: Int = 0
    lateinit var barangAdapter : BarangAdapter
    var stok : Int = 0
    var namaBarang : String = ""
    var kodebarang : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)
        setupRecyclerView()
        setupView()
        chekOut()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Transaksi")

    }

    fun kurangiStok(){
        CoroutineScope(Dispatchers.IO).launch {
            db.barangDao().updateBarang(
                Barang(barangId, kodebarang, namaBarang, stok - 1)
            )
        }
    }

    private fun setupRecyclerView(){
        barangAdapter = BarangAdapter(arrayListOf(), object : BarangAdapter.OnAdapterListener{
            override fun onUpdate(barang: Barang) {
            }

            override fun onDelete(barang: Barang) {
            }

            override fun onKurangiStok(barang: Barang) {
                barangId = barang.id
                namaBarang = barang.nama
                kodebarang = barang.kode
                stok = barang.jumlah
                kurangiStok()
                Toast.makeText(applicationContext, "$namaBarang Berhasil dimasukan kedalam keranjang", Toast.LENGTH_SHORT).show()
            }

        }, Constant.TRANSAKSI)
        rv_barang_transaksi.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = barangAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        loadBarang()
    }

    fun loadBarang(){
        CoroutineScope(Dispatchers.IO).launch {
            val barangs = db.barangDao().getBarangs()
            withContext(Dispatchers.Main){
                barangAdapter.setData(barangs)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun chekOut(){
        btn_chekout.setOnClickListener {
            startActivity(Intent(applicationContext, CheckoutActivity::class.java))
        }
    }
}