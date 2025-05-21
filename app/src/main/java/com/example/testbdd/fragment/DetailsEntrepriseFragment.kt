package com.example.testbdd.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testbdd.R
import com.example.testbdd.adapter.EntrepriseAdapter
import com.example.testbdd.adapter.DetailEntrepriseAdapter
import com.example.testbdd.api.ApiClient
import com.example.testbdd.api.SupabaseApi
import com.example.testbdd.model.Entreprise
import com.example.testbdd.model.DetailEntreprise
import com.example.testbdd.model.CreateDetailEntreprise
import kotlinx.coroutines.launch

class DetailsEntrepriseFragment : Fragment() {
    private lateinit var supabaseApi: SupabaseApi
    private lateinit var entrepriseAdapter: EntrepriseAdapter
    private lateinit var detailAdapter: DetailEntrepriseAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var inscriptionButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var titleText: TextView
    private lateinit var subtitleText: TextView
    
    private var entrepriseSelectionnee: Entreprise? = null
    private var candidatId: Int = 1

    companion object {
        private const val TAG = "DetailsEntrepriseFragment"
        private const val ARG_CANDIDAT_ID = "candidat_id"

        fun newInstance(candidatId: Int): DetailsEntrepriseFragment {
            val fragment = DetailsEntrepriseFragment()
            val args = Bundle()
            args.putInt(ARG_CANDIDAT_ID, candidatId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            candidatId = it.getInt(ARG_CANDIDAT_ID, 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_entreprise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupAdapters()
        setupListeners()
        initializeApi()
        inscriptionButton.visibility = View.GONE
        chargerEntreprises()
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.detailsRecyclerView)
        inscriptionButton = view.findViewById(R.id.inscriptionButton)
        progressBar = view.findViewById(R.id.progressBar)
        titleText = view.findViewById(R.id.titleText)
        subtitleText = view.findViewById(R.id.subtitleText)
        
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupAdapters() {
        entrepriseAdapter = EntrepriseAdapter { entreprise ->
            entrepriseSelectionnee = entreprise
            chargerListeAttente(entreprise.id)
        }
        
        detailAdapter = DetailEntrepriseAdapter { detail, isChecked ->
            updateDetailEntreprise(detail.copy(checkbox = isChecked))
        }
    }

    private fun setupListeners() {
        inscriptionButton.setOnClickListener {
            entrepriseSelectionnee?.let { entreprise ->
                sinscrire(entreprise.id)
            }
        }
    }

    private fun initializeApi() {
        supabaseApi = ApiClient.retrofit.create(SupabaseApi::class.java)
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        recyclerView.visibility = if (show) View.GONE else View.VISIBLE
        inscriptionButton.isEnabled = !show
    }

    private fun chargerEntreprises() {
        showLoading(true)
        inscriptionButton.visibility = View.GONE
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = supabaseApi.getEntreprises()
                if (response.isSuccessful) {
                    val entreprises = response.body()
                    recyclerView.adapter = entrepriseAdapter
                    entrepriseAdapter.updateEntreprises(entreprises ?: emptyList())
                    titleText.text = getString(R.string.title_companies_list)
                    subtitleText.text = getString(R.string.subtitle_companies_list)
                } else {
                    Toast.makeText(requireContext(), 
                        getString(R.string.error_loading_companies), 
                        Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors du chargement des entreprises", e)
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun chargerListeAttente(entrepriseId: Int) {
        showLoading(true)
        inscriptionButton.visibility = View.VISIBLE
        
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = supabaseApi.getDetailsEntrepriseByEntreprise(
                    idEntreprise = "eq.$entrepriseId",
                    select = "*"
                )

                if (response.isSuccessful) {
                    val details = response.body()
                    recyclerView.adapter = detailAdapter
                    detailAdapter.submitList(details ?: emptyList())
                    detailAdapter.updateEntreprises(mapOf(entrepriseId to (entrepriseSelectionnee?.nom ?: "")))
                    
                    // Charger les informations des candidats
                    details?.forEach { detail ->
                        val candidatResponse = supabaseApi.getCandidats(
                            id = "eq.${detail.id_candidats}",
                            select = "id,nom,prenom"
                        )
                        if (candidatResponse.isSuccessful) {
                            val candidats = candidatResponse.body()
                            if (!candidats.isNullOrEmpty()) {
                                detailAdapter.updateCandidats(candidats)
                            }
                        }
                    }
                    
                    titleText.text = getString(R.string.title_waiting_list, entrepriseSelectionnee?.nom ?: "")
                    subtitleText.text = getString(R.string.subtitle_waiting_list, entrepriseSelectionnee?.nom ?: "")
                } else {
                    Toast.makeText(requireContext(), 
                        getString(R.string.error_loading_waiting_list, response.code()), 
                        Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception lors du chargement de la liste d'attente", e)
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun sinscrire(entrepriseId: Int) {
        showLoading(true)
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val detailEntreprise = CreateDetailEntreprise(
                    id_entreprises = entrepriseId,
                    id_candidats = candidatId,
                    checkbox = false
                )
                
                val createResponse = supabaseApi.createDetailEntreprise(detailEntreprise)
                if (createResponse.isSuccessful) {
                    val createdDetails = createResponse.body()
                    if (!createdDetails.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), 
                            getString(R.string.inscription_success), 
                            Toast.LENGTH_SHORT).show()
                        
                        // Recharger la liste d'attente
                        chargerListeAttente(entrepriseId)
                    } else {
                        Toast.makeText(requireContext(), 
                            getString(R.string.error_inscription), 
                            Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = createResponse.errorBody()?.string()
                    Log.e(TAG, "Erreur inscription: $errorBody")
                    Toast.makeText(requireContext(), 
                        getString(R.string.error_inscription), 
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception lors de l'inscription", e)
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun updateDetailEntreprise(detail: DetailEntreprise) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = supabaseApi.updateDetailEntreprise(detail.id, detail)
                if (!response.isSuccessful) {
                    Toast.makeText(requireContext(), 
                        getString(R.string.error_updating), 
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de la mise à jour", e)
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
} 