package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SynonymAdapter : RecyclerView.Adapter<SynonymViewHolder>() {

    private val items: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SynonymViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_synonyms, parent, false)
        return SynonymViewHolder(view)
    }

    override fun onBindViewHolder(holder: SynonymViewHolder, position: Int) {
        val currentItem = items[position]
        holder.synonymView.text = currentItem
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateSynonym(updatedSynonyms: ArrayList<String>) {
        items.clear()
        items.addAll(updatedSynonyms)

        notifyDataSetChanged()
    }
}

class SynonymViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val synonymView: TextView = itemView.findViewById(R.id.synonym)
}