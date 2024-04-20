package com.example.reblymegibtabuni

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegeaAdapter(private val context: Context, private val regea: List<Regea>, val listener: (Regea) -> Unit)
    : RecyclerView.Adapter<RegeaAdapter.RegeaViewHolder>(){

    class RegeaViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imgRegea = view.findViewById<ImageView>(R.id.img_item_photo)
        val  nameRegea = view.findViewById<TextView>(R.id.tv_item_name)
        val  descRegea = view.findViewById<TextView>(R.id.tv_item_description)

        fun bindview(regea: Regea){
            imgRegea.setImageResource(regea.imgRegea)
            nameRegea.text = regea.nameRegea
            descRegea.text = regea.descRegea
            itemView.setOnClickListener {
                listener(regea)
            }
        }

        private fun listener(regea: Regea) {
            listener(regea)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegeaViewHolder {
        return RegeaViewHolder(
            LayoutInflater.from(context).inflate(R.layout.example_item, parent, false)
        )
    }

    override fun getItemCount(): Int = regea.size

    override fun onBindViewHolder(holder: RegeaViewHolder, position: Int) {
        holder.bindview(regea[position])
        holder.itemView.setOnClickListener { listener(regea[position]) }
    }

}