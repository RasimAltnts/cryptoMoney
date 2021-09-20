package com.example.kriptopara.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    //@SerializedName("currency") Java SerialzedName gelecek verinin tag'ın atanıcak degişkenler aynı olması gerekir.
    val currency:String,
    //@SerializedName("price")
    val price:String) {

}