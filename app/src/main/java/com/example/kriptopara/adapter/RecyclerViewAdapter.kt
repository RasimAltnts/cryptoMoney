package com.example.kriptopara.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kriptopara.R
import com.example.kriptopara.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*


class RecyclerViewAdapter(private val cryptoList:ArrayList<CryptoModel>,private val listener:Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    val colors:Array<String> = arrayOf("#57385c","#a75265","#ec7263","#febe7e","#740021","#8d0033","#bd3246","#fdc8aa")
    class RowHolder(view:View) : RecyclerView.ViewHolder(view){

        fun bind(cryptoModel: CryptoModel, color: Array<String>, position: Int,listener:Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(color[position % 8]))
            itemView.text_name.text = cryptoModel.currency
            itemView.text_price.text = cryptoModel.price

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],color = colors,position,listener)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

}