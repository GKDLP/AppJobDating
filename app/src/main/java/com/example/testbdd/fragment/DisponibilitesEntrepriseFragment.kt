package com.example.testbdd.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testbdd.R
import com.example.testbdd.adapter.DisponibiliteAdapter
import com.example.testbdd.api.ApiClient
import com.example.testbdd.api.SupabaseApi
import com.example.testbdd.model.Entreprise
import kotlinx.coroutines.launch

class DisponibilitesEntrepriseFragment : Fragment() {
    private lateinit var supabaseApi: SupabaseApi
    private lateinit var disponibiliteAdapter: DisponibiliteAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var titreEntreprise: TextView
    private var entreprise: Entreprise? = null

    companion object {
        private const val ARG_ENTREPRISE = "entreprise"

        fun newInstance(entreprise: Entreprise): DisponibilitesEntrepriseFragment {
            return DisponibilitesEntrepriseFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ENTREPRISE, entreprise)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            entreprise = it.getParcelable(ARG_ENTREPRISE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disponibilites_entreprise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisation des vues
        recyclerView = view.findViewById(R.id.disponibilitesRecyclerView)
        titreEntreprise = view.findViewById(R.id.titreEntreprise)

        // Configuration du RecyclerView
        disponibiliteAdapter = DisponibiliteAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = disponibiliteAdapter
        }

        // Initialisation de l'API
        supabaseApi = ApiClient.retrofit.create(SupabaseApi::class.java)

        // Affichage du nom de l'entreprise
        entreprise?.let {
            titreEntreprise.text = it.nom
            chargerDisponibilites(it.id)
        }
    }

    private fun chargerDisponibilites(entrepriseId: Int) {
        lifecycleScope.launch {
            try {
                val response = supabaseApi.getDisponibilitesEntreprise(entrepriseId)
                if (response.isSuccessful) {
                    val disponibilites = response.body()
                    if (disponibilites.isNullOrEmpty()) {
                        Toast.makeText(context, 
                            "Aucune disponibilité trouvée", 
                            Toast.LENGTH_LONG).show()
                    } else {
                        disponibiliteAdapter.updateDisponibilites(disponibilites)
                    }
                } else {
                    Toast.makeText(context, 
                        "Erreur lors de la récupération des disponibilités", 
                        Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, 
                    "Erreur: ${e.message}", 
                    Toast.LENGTH_LONG).show()
            }
        }
    }
} 