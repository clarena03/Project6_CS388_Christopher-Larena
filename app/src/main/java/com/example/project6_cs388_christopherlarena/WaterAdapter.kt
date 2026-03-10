package com.example.project6_cs388_christopherlarena

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project6_cs388_christopherlarena.databinding.ItemWaterEntryBinding
import java.text.DateFormat
import java.util.Date

class WaterAdapter : ListAdapter<WaterEntry, WaterAdapter.WaterViewHolder>(DiffCallback) {

    class WaterViewHolder(private val binding: ItemWaterEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: WaterEntry) {
            binding.textViewAmount.text = "${entry.amount} oz"
            binding.textViewTimestamp.text = DateFormat.getDateTimeInstance().format(Date(entry.timestamp))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterViewHolder {
        val binding = ItemWaterEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WaterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WaterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<WaterEntry>() {
            override fun areItemsTheSame(oldItem: WaterEntry, newItem: WaterEntry): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WaterEntry, newItem: WaterEntry): Boolean {
                return oldItem == newItem
            }
        }
    }
}
