package com.example.testbdd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testbdd.R
import com.example.testbdd.model.Disponibilite

class DisponibiliteAdapter : RecyclerView.Adapter<DisponibiliteAdapter.DisponibiliteViewHolder>() {
    
    private var disponibilites: List<Disponibilite> = emptyList()

    fun updateDisponibilites(newDisponibilites: List<Disponibilite>) {
        disponibilites = newDisponibilites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisponibiliteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_disponibilite, parent, false)
        return DisponibiliteViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisponibiliteViewHolder, position: Int) {
        val disponibilite = disponibilites[position]
        holder.bind(disponibilite)
    }

    override fun getItemCount(): Int = disponibilites.size

    class DisponibiliteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateDisponibilite)
        private val heureTextView: TextView = itemView.findViewById(R.id.heureDisponibilite)

        fun bind(disponibilite: Disponibilite) {
            dateTextView.text = disponibilite.date
            heureTextView.text = "${disponibilite.heure_debut} - ${disponibilite.heure_fin}"
        }
    }
} 