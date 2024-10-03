package com.ieseljust.pmdm.comptadorMVVM

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer

class MostraComptadorActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Enllacem la vista
        setContentView(R.layout.activity_mostra_comptador)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Establim les referencies a les diferents vistes de la interficie
        val textViewContador = findViewById<TextView>(R.id.textViewMostraComptador)
        val btBack = findViewById<Button>(R.id.btBack)

        // Agafem la informacio de la Intent
        val comptador:Int? = intent.getIntExtra("comptador", 0)

        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.text=comptador.toString()

        btBack.setOnClickListener {
            finish()
        }

    }
}