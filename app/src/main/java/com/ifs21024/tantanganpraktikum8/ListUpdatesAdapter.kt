package com.ifs21024.tantanganpraktikum8

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21024.tantanganpraktikum8.databinding.ItemRowUpdatesBinding

class ListUpdatesAdapter(private val listUpdates: ArrayList<Updates>) :
    RecyclerView.Adapter<ListUpdatesAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowUpdatesBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val updates = listUpdates[position]
        holder.binding.ivUpdates.setImageResource(updates.image)
        holder.binding.tvProfileUpdates.text = updates.profile
        holder.binding.tvTimeUpdates.text = updates.time
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listUpdates[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUpdates.size

    class ListViewHolder(var binding: ItemRowUpdatesBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Updates)
    }
}