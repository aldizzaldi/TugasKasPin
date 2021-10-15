package com.example.tugaskaspin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)
        setupRecyclerView()
        setupView()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Transaksi")

    }

    fun kurangiStok(){
        barangId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           db.barangDao().kurangiStok(barangId)
        }
    }

    private fun setupRecyclerView(){
        barangAdapter = BarangAdapter(arrayListOf(), object : BarangAdapter.OnAdapterListener{
            override fun onUpdate(barang: Barang) {
//                intentEdit(barang.id, Constant.TYPE_UPDATE)
//                Toast.makeText(applicationContext, barang.id.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(barang: Barang) {
//                deleteDialog(barang)
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
}