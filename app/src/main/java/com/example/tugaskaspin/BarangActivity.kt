package com.example.tugaskaspin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BarangActivity : AppCompatActivity() {

    private lateinit var fab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)

        fab = findViewById(R.id.addBarangFAB)

        fab.setOnClickListener {
            startActivity(Intent(this, AddBarangActivity::class.java))
        }

    }
}