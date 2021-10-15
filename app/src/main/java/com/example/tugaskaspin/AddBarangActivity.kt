package com.example.tugaskaspin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tugaskaspin.room.Barang
import com.example.tugaskaspin.room.BarangDB
import kotlinx.android.synthetic.main.activity_add_barang.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBarangActivity : AppCompatActivity() {

    private lateinit var btnAddBarang : Button
    val db by lazy { BarangDB(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_barang)

        btnAddBarang = findViewById(R.id.btn_tambah_barang)

        addBarang()
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
}