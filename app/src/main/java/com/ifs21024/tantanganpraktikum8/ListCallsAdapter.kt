package com.ifs21024.tantanganpraktikum8

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21024.tantanganpraktikum8.databinding.ItemRowCallsBinding

class ListCallsAdapter(private val listCalls: ArrayList<Calls>) :
    RecyclerView.Adapter<ListCallsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowCallsBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val calls = listCalls[position]
        holder.binding.ivProfileCalls.setImageResource(calls.image)
        holder.binding.tvProfileCalls.text = calls.profile
        holder.binding.tvTimeCalls.text = calls.time
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listCalls[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listCalls.size

    class ListViewHolder(var binding: ItemRowCallsBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Calls)
    }
}