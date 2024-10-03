package com.pmdm.ieseljust.comptador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var comptador=0
    lateinit var textViewContador: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("onCreate", "s'ha executat onCreate")
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia al TextView
        textViewContador=findViewById<TextView>(R.id.textViewComptador)
        // Referencia al boto d'Open
        val btOpen=findViewById<Button>(R.id.btOpen)

        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.text=comptador.toString() // Estem fent una assignacio directament o accedinta algun metode?

        // Referencia al botón
        val btAdd=findViewById<Button>(R.id.btAdd)

        // Asociaciamos una expresióin lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btAdd.setOnClickListener {
            comptador++
            textViewContador.text=comptador.toString()
        }

        //Referencia al botó Substract
        val btSubstract=findViewById<Button>(R.id.btSubtract)

        btSubstract.setOnClickListener{
            comptador--
            textViewContador.text=comptador.toString()
        }

        //Referencia al botó
        val btReset=findViewById<Button>(R.id.btReset)

        btReset.setOnClickListener{
            comptador=0
            textViewContador.text=comptador.toString()
        }

        /*btOpen.setOnClickListener{
            val intent = Intent(baseContext, MostraComptadorActivity::class.java)
            intent.putExtra("comptador", comptador)
            startActivity(intent)
        }*/

        btOpen.setOnClickListener {
            Intent(baseContext, MostraComptadorActivity::class.java).apply {
                putExtra("comptador", comptador)
                startActivity(this)
            }
        }
    }

    //Per guardar i restaurar valors
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Codi per a guardar l'estat
        outState.putInt("COMPTADOR", comptador)
        Log.i("onSaveInstanceState", "s'ha executat onSaveInstanceState")
    }

    override fun onRestoreInstanceState(estat: Bundle) {
        super.onRestoreInstanceState(estat)
        // Codi per a guardar l'estat
        comptador=estat.getInt("COMPTADOR")

        //Restaurar l'estat del comptador
        textViewContador=findViewById<TextView>(R.id.textViewComptador)
        textViewContador.text=comptador.toString()

        Log.i("onRestoreInstanceState", "s'ha executat onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.i("onStart", "s'ha executat onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("onResume", "s'ha executat onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("onPause", "s'ha executat onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("onStop", "s'ha executat onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("onRestart", "s'ha executat onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy", "s'ha executat onDestroy")
    }

}