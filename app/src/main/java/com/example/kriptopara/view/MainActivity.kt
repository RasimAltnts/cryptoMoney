package com.example.kriptopara.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kriptopara.R
import com.example.kriptopara.adapter.RecyclerViewAdapter
import com.example.kriptopara.model.CryptoModel
import com.example.kriptopara.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModels:ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //API key :938ff15e014535452724ff64885ce77db54602a3
        //https://api.nomics.com/v1/prices?key=938ff15e014535452724ff64885ce77db54602a3
        //https://raw.githubusercontent.com/RasimAltnts/cryptoMoney/main/crypto-Prices.json

        //recycleView

        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        loadData()




    }
    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    //response null olup olmadığı kontrol ediliyor .let ile
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        for(cryptoModel:CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }
                        recyclerViewAdapter = RecyclerViewAdapter(cryptoModels!!,this@MainActivity)
                        recyclerView.adapter = recyclerViewAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}