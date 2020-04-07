package com.dev.cosmina.githubapi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.cosmina.githubapi.R
import com.dev.cosmina.githubapi.ui.model.RepositoryItem
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.repository_item, parent, false)) {

    fun bind(repoItem: RepositoryItem, clickListener: OnItemClickListener) {
        itemView.apply {
            repo_name.text = repoItem.name
            repo_description.text = repoItem.description
            repo_star_count.text = repoItem.stargazers_count.toString()
            repo_language.text = repoItem.language
            setOnClickListener {
                clickListener.onItemClicked(repoItem)
            }
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(repoItem: RepositoryItem)
}