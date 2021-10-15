package com.example.tugaskaspin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaskaspin.room.Barang
import com.example.tugaskaspin.room.BarangDB
import com.example.tugaskaspin.room.Constant
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
            val barangs = db.barangDao().getBarangs()
            withContext(Dispatchers.Main){
                barangAdapter.setData(barangs)
            }
        }
    }

    fun setupListener(){
        fab.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    private fun setupRecyclerView(){
        barangAdapter = BarangAdapter(arrayListOf(), object : BarangAdapter.OnAdapterListener{
            override fun onUpdate(barang: Barang) {
                intentEdit(barang.id, Constant.TYPE_UPDATE)
//                Toast.makeText(applicationContext, barang.id.toString(), Toast.LENGTH_SHORT).show()
            }

        })
        rv_barang.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = barangAdapter
        }
    }

    fun intentEdit(barangId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddBarangActivity::class.java)
            .putExtra("intent_id", barangId)
            .putExtra("intent_type", intentType)
        )
    }
}