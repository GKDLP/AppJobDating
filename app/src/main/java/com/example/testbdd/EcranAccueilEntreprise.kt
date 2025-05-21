package com.example.testbdd

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.testbdd.R
import com.example.testbdd.api.ApiClient
import com.example.testbdd.api.SupabaseApi
import kotlinx.coroutines.launch

class EcranAccueilEntreprise : AppCompatActivity() {
    private lateinit var supabaseApi: SupabaseApi
    private val TAG = "EcranAccueilEntreprise"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecran_accueil_entreprise)

        try {
            // Configuration de l'ActionBar
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                
                val titleTextView = TextView(this@EcranAccueilEntreprise).apply {
                    text = "Accueil entreprise"
                    textSize = 20f
                    setTextColor(ContextCompat.getColor(context, R.color.title_text_color))
                    gravity = Gravity.CENTER
                    typeface = android.graphics.Typeface.DEFAULT_BOLD
                }

                customView = titleTextView
                setDisplayShowCustomEnabled(true)
                setDisplayShowTitleEnabled(false)
            }

            // Initialisation de l'API
            supabaseApi = ApiClient.retrofit.create(SupabaseApi::class.java)

        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate", e)
            Toast.makeText(this, "Une erreur est survenue: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return true
        }
        onBackPressed()
        return true
    }
} 