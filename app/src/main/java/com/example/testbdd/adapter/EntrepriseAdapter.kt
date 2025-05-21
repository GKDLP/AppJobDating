package com.example.testbdd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testbdd.R
import com.example.testbdd.model.Entreprise

class EntrepriseAdapter(private val onItemClick: (Entreprise) -> Unit) : 
    RecyclerView.Adapter<EntrepriseAdapter.ViewHolder>() {

    private var entreprises: List<Entreprise> = emptyList()

    fun updateEntreprises(newEntreprises: List<Entreprise>) {
        entreprises = newEntreprises
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entreprise, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entreprises[position])
    }

    override fun getItemCount() = entreprises.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomEntreprise: TextView = itemView.findViewById(R.id.nomEntreprise)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(entreprises[position])
                }
            }
        }

        fun bind(entreprise: Entreprise) {
            nomEntreprise.text = entreprise.nom
        }
    }
} 