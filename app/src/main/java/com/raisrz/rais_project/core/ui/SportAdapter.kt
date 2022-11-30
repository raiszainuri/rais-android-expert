package com.raisrz.rais_project.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raisrz.rais_project.R
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import com.raisrz.rais_project.databinding.ItemListSportBinding
import java.util.ArrayList

class SportAdapter : RecyclerView.Adapter<SportAdapter.ListViewHolder>() {

    private var listData = ArrayList<SportEntity>()
    //private lateinit var onLongItemClickCallback: OnLongItemClickCallback
    var onItemClick: ((SportEntity) -> Unit)? = null
    var onLongItemClick: ((SportEntity) -> Unit)? = null

//    interface OnLongItemClickCallback {
//        fun onLongItemClicked(data: SportEntity)
//    }

    fun setData(newListData: List<SportEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

//    fun setOnLongItemClickCallback(onLongItemClickCallback: OnLongItemClickCallback) {
//        this.onLongItemClickCallback = onLongItemClickCallback
//    }

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
        fun bind(data: SportEntity) {
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