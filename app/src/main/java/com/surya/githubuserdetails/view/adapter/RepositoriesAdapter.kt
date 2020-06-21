package com.surya.githubuserdetails.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surya.githubuserdetails.R
import com.surya.githubuserdetails.model.repository.RepositoriesItem
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoriesAdapter(
    private val repositoriesList: ArrayList<RepositoriesItem>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>() {

    class RepositoryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(repositoriesItem: RepositoriesItem, clickListener: OnItemClickListener) {
            view.repo_name.text = repositoriesItem.name
            view.repo_description.text = repositoriesItem.description
            itemView.setOnClickListener {
                clickListener.onItemClicked(repositoriesItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = repositoriesList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repositoriesItem = repositoriesList[position]
        holder.bind(repositoriesItem, itemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClicked(repositoriesItem: RepositoriesItem)
    }
}