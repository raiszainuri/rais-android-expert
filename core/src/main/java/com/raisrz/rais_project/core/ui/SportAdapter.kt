package com.raisrz.rais_project.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raisrz.rais_project.core.R
import com.raisrz.rais_project.core.databinding.ItemListSportBinding
import com.raisrz.rais_project.core.domain.model.Sport

class SportAdapter : RecyclerView.Adapter<SportAdapter.ListViewHolder>() {

    private var listData = ArrayList<Sport>()
    var onItemClick: ((Sport) -> Unit)? = null
    var onLongItemClick: ((Sport) -> Unit)? = null

    fun setData(newListData: List<Sport>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_sport, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSportBinding.bind(itemView)
        fun bind(data: Sport) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.strSportThumb)
                    .into(ivImage)
                tvSportName.text = data.strSport
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
            binding.root.setOnLongClickListener {
                onLongItemClick?.invoke(listData[adapterPosition])
                true
            }
        }
    }
}