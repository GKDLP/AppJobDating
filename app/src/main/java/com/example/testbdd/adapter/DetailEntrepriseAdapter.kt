package com.example.testbdd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testbdd.R
import com.example.testbdd.model.DetailEntreprise
import com.example.testbdd.model.Candidat

class DetailEntrepriseAdapter(
    private val onCheckboxChanged: (DetailEntreprise, Boolean) -> Unit
) : ListAdapter<DetailEntreprise, DetailEntrepriseAdapter.ViewHolder>(DetailEntrepriseDiffCallback()) {

    private var entreprises: Map<Int, String> = emptyMap()
    private var candidats: Map<Int, Candidat> = emptyMap()

    fun updateEntreprises(newEntreprises: Map<Int, String>) {
        entreprises = newEntreprises
        notifyDataSetChanged()
    }

    fun updateCandidats(newCandidats: List<Candidat>) {
        candidats = newCandidats.associateBy { it.id }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_entreprise, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomCandidat: TextView = itemView.findViewById(R.id.NomCandidats)
        private val nomEntreprise: TextView = itemView.findViewById(R.id.nomEntreprise)
        private val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)

        fun bind(detail: DetailEntreprise) {
            // Afficher le nom du candidat
            val candidat = candidats[detail.id_candidats]
            if (candidat != null) {
                nomCandidat.text = "${candidat.prenom} ${candidat.nom}"
            }

            // Afficher le nom de l'entreprise
            nomEntreprise.text = entreprises[detail.id_entreprises] ?: "Entreprise inconnue"

            // Gérer la checkbox
            checkbox.isChecked = detail.checkbox
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckboxChanged(detail, isChecked)
            }
        }
    }

    private class DetailEntrepriseDiffCallback : DiffUtil.ItemCallback<DetailEntreprise>() {
        override fun areItemsTheSame(oldItem: DetailEntreprise, newItem: DetailEntreprise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DetailEntreprise, newItem: DetailEntreprise): Boolean {
            return oldItem == newItem
        }
    }
} 