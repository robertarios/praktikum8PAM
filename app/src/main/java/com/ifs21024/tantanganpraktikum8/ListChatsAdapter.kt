package com.ifs21024.tantanganpraktikum8

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21024.tantanganpraktikum8.databinding.ItemRowChatsBinding

class ListChatsAdapter(private val listChats: ArrayList<Chats>) :
    RecyclerView.Adapter<ListChatsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowChatsBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val chats = listChats[position]
        holder.binding.ivProfileChats.setImageResource(chats.image)
        holder.binding.tvProfileChats.text = chats.profile
        holder.binding.tvMessages.text = chats.message
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listChats[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listChats.size

    class ListViewHolder(var binding: ItemRowChatsBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Chats)
    }
}