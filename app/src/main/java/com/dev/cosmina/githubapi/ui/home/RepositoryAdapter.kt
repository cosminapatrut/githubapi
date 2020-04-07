package com.dev.cosmina.githubapi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.cosmina.githubapi.ui.model.RepositoryItem

class RepositoryAdapter(private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<RepositoryViewHolder>() {

    private var items: List<RepositoryItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepositoryViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

    fun replaceItems(newItems: List<RepositoryItem>) {
        items = newItems
        notifyDataSetChanged()
    }

}