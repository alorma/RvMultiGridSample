package com.alorma.rvmultigridsample;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DimensionalAdapter<K>(private val items: List<K>,
                            private val inflater: LayoutInflater,
                            private val callback: (RecyclerView, View, K, Int) -> Unit) : RecyclerView.Adapter<DimensionalAdapter.ViewHolder<K>>() {

    override fun onBindViewHolder(holder: ViewHolder<K>?, position: Int) {
        holder?.populate(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = ViewHolder(inflater.inflate(R.layout.recycler_item, parent, false), callback)

    override fun getItemCount() = items.size

    class ViewHolder<in K>(itemView: View,
                           private val callback: (RecyclerView, View, K, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val recycler: RecyclerView by lazy { itemView.findViewById<RecyclerView>(R.id.pageRecycler) }

        fun populate(k: K) {
            callback.invoke(recycler, itemView, k, adapterPosition)
        }
    }
}

