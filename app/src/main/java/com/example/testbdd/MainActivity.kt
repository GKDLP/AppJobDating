package com.example.testbdd

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.testbdd.R
import com.example.testbdd.api.ApiClient
import com.example.testbdd.api.SupabaseApi
import com.example.testbdd.EcranAccueilCandidats
import com.example.testbdd.EcranAccueilEntreprise
import com.example.testbdd.EcranAccueilOperateur

class MainActivity : AppCompatActivity() {
    private lateinit var supabaseApi: SupabaseApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Définir et centrer le titre
        supportActionBar?.apply {
            // Centre le titre en utilisant une vue personnalisée
            val titleTextView = TextView(this@MainActivity).apply {
                text = "JobDating"
                textSize = 20f
                setTextColor(ContextCompat.getColor(context, R.color.title_text_color))
                gravity = Gravity.CENTER
                typeface = android.graphics.Typeface.DEFAULT_BOLD
            }

            // Applique la vue personnalisée comme vue personnalisée de l'ActionBar
            customView = titleTextView
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        // Initialisation de l'API
        supabaseApi = ApiClient.retrofit.create(SupabaseApi::class.java)

        //Choisir le type d'utilisateur
        findViewById<Button>(R.id.button3).setOnClickListener {
            val intent = Intent(this, EcranAccueilEntreprise::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val intent = Intent(this, EcranAccueilCandidats::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, EcranAccueilOperateur::class.java)
            startActivity(intent)
        }
    }
}