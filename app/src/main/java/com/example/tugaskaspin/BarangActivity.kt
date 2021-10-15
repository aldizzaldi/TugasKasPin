package com.example.tugaskaspin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaskaspin.room.BarangDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_barang.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BarangActivity : AppCompatActivity() {

    private lateinit var fab : FloatingActionButton
    lateinit var barangAdapter : BarangAdapter
    val db by lazy { BarangDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)

        fab = findViewById(R.id.addBarangFAB)

        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val barangs = db.barangDao().getDatas()
            withContext(Dispatchers.Main){
                barangAdapter.setData(barangs)
            }
        }
    }

    fun setupListener(){
        fab.setOnClickListener {
            startActivity(Intent(this, AddBarangActivity::class.java))
        }
    }

    private fun setupRecyclerView(){
        barangAdapter = BarangAdapter(arrayListOf())
        rv_barang.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = barangAdapter
        }

    }
}