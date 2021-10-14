package com.example.tugaskaspin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnBarang : Button
    private lateinit var btnTransaksi : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBarang = findViewById(R.id.button_barang)
        btnTransaksi = findViewById(R.id.button_transaksi)

        btnBarang.setOnClickListener {
            startActivity(Intent(this, BarangActivity::class.java))
        }

        btnTransaksi.setOnClickListener {
            startActivity(Intent(this,TransaksiActivity::class.java))
        }

    }
}