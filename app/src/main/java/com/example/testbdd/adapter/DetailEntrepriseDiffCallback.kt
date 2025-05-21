package com.example.testbdd.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.testbdd.model.DetailEntreprise

class DetailEntrepriseDiffCallback : DiffUtil.ItemCallback<DetailEntreprise>() {
    override fun areItemsTheSame(oldItem: DetailEntreprise, newItem: DetailEntreprise): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DetailEntreprise, newItem: DetailEntreprise): Boolean {
        return oldItem == newItem
    }
} 