package com.example.tugaskaspin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tugaskaspin.room.Barang
import com.example.tugaskaspin.room.BarangDB
import com.example.tugaskaspin.room.Constant
import kotlinx.android.synthetic.main.activity_add_barang.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBarangActivity : AppCompatActivity() {

    private lateinit var btnAddBarang : Button
    val db by lazy { BarangDB(this) }
    private var barangId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_barang)
        btnAddBarang = findViewById(R.id.btn_tambah_barang)
        setupView()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType) {
            Constant.TYPE_CREATE -> {
                addBarang()
            }
            Constant.TYPE_UPDATE -> {
                tv_judul_barang.text = "Edit Barang"
                getBarang()
                editBarang()
            }
        }
    }

    fun addBarang(){
        btnAddBarang.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.barangDao().addBarang(
                    Barang(0, et_kode_barang.text.toString(), et_nama_barang.text.toString(), et_stok_barang.text.toString().toInt())
                )
                finish()
            }
        }
    }

    fun editBarang(){
        btnAddBarang.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.barangDao().updateBarang(
                    Barang(barangId, et_kode_barang.text.toString(), et_nama_barang.text.toString(), et_stok_barang.text.toString().toInt())
                )
                finish()
            }
        }
    }

    fun getBarang(){
        barangId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           val barang = db.barangDao().getBarang(barangId)[0]
            et_kode_barang.setText(barang.kode)
            et_nama_barang.setText(barang.nama)
            et_stok_barang.setText(barang.jumlah.toString())

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}