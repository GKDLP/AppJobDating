package com.example.testbdd

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.testbdd.R

class EcranAccueilOperateur : AppCompatActivity() {
    private val TAG = "EcranAccueilOperateur"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecran_accueil_operateur)

        try {
            // Configuration de l'ActionBar
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                
                val titleTextView = TextView(this@EcranAccueilOperateur).apply {
                    text = "Accueil opérateur"
                    textSize = 20f
                    setTextColor(ContextCompat.getColor(context, R.color.title_text_color))
                    gravity = Gravity.CENTER
                    typeface = android.graphics.Typeface.DEFAULT_BOLD
                }

                customView = titleTextView
                setDisplayShowCustomEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
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