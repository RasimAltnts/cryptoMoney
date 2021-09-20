package com.example.kriptopara.service

import com.example.kriptopara.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //GET,POST,UPDATE,DELETE
     @GET("prices?key=938ff15e014535452724ff64885ce77db54602a3")
     fun getData():Call<List<CryptoModel>>//Asynronic veri indirme işlemi için tanımlanır.
}