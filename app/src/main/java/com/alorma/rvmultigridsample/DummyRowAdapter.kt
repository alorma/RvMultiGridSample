package com.alorma.rvmultigridsample;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DummyRowAdapter(val items: List<String>, val inflater: LayoutInflater) : RecyclerView.Adapter<DummyRowAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.populate(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = ViewHolder(inflater.inflate(R.layout.page_item, parent, false))

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val pageText: TextView by lazy { itemView.findViewById<TextView>(R.id.pageText) }

        fun populate(text: String) {
            pageText.text = text
        }
    }
}

