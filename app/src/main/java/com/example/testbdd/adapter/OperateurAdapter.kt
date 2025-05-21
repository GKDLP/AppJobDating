package com.example.testbdd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testbdd.R
import com.example.testbdd.model.Operateur

class OperateurAdapter : RecyclerView.Adapter<OperateurAdapter.OperateurViewHolder>() {
    
    private var operateurs: List<Operateur> = emptyList()

    fun updateOperateurs(newOperateurs: List<Operateur>) {
        operateurs = newOperateurs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperateurViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_operateur, parent, false)
        return OperateurViewHolder(view)
    }

    override fun onBindViewHolder(holder: OperateurViewHolder, position: Int) {
        val operateur = operateurs[position]
        holder.bind(operateur)
    }

    override fun getItemCount(): Int = operateurs.size

    class OperateurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomTextView: TextView = itemView.findViewById(R.id.nomOperateur)

        fun bind(operateur: Operateur) {
            nomTextView.text = operateur.nom
        }
    }
} 