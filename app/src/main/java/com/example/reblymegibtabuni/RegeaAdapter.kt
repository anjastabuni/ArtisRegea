package com.example.reblymegibtabuni

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegeaAdapter(private val context: Context, var regeaList: List<Gambar>)
    : RecyclerView.Adapter<RegeaAdapter.ListViewHolder>{

   inner class ListViewHolder(var v: View): RecyclerView.ViewHolder(v) {
        val imgT = v.findViewById<ImageView>(R.id.img_item_photo)
       val nameT = v.findViewById<TextView>(R.id.tv_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.example_item, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val newList = regeaList[position]
        holder.nameT.text = newList.name
        holder.imgT.loadImage(newList.imageUrl)
        holder.v.setOnClickListener {
            val name = newList.name
            val  descrip = newList.description
            val imgUri = newList.imageUrl

            val mIntent = Intent(mContext, ActivityDetail::class.java)
            mIntent.putExtra("NAMET", name)
            mIntent.putExtra("DESCRIPT",descrip)
            mIntent.putExtra("IMGHURI", imgUri)
            mContext.StartActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return regeaList.size
    }
}