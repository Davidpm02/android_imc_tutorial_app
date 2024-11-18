package com.example.imc_tutorial_app

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc_tutorial_app.R
import com.example.imc_tutorial_app.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResultIMC: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvIMCDescription: TextView
    private lateinit var btnRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resultIMC:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initListeners()
        initUI(resultIMC)

    }

    private fun initUI(resultIMC:Double) {

        tvIMC.text = resultIMC.toString()
        when(resultIMC){
            in 0.00..18.50 -> {
                tvResultIMC.text = getString(R.string.title_bajo_peso)
                tvResultIMC.setTextColor(getColor(R.color.imc_bajo_peso))
                tvIMCDescription.text = getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 -> {
                tvResultIMC.text = getString(R.string.title_peso_normal)
                tvResultIMC.setTextColor(getColor(R.color.imc_peso_normal))
                tvIMCDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> {
                tvResultIMC.text = getString(R.string.title_sobre_peso)
                tvResultIMC.setTextColor(getColor(R.color.imc_sobre_peso))
                tvIMCDescription.text = getString(R.string.description_sobre_peso)
            }
            in 30.00..99.00 -> {
                tvResultIMC.text = getString(R.string.title_obesidad)
                tvResultIMC.setTextColor(getColor(R.color.imc_obesidad))
                tvIMCDescription.text = getString(R.string.description_obesidad)
            }
            else -> {
                tvIMC.text = getString(R.string.error)
                tvResultIMC.text = getString(R.string.error)
                tvResultIMC.setTextColor(getColor(R.color.imc_obesidad))
                tvIMCDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents(){

        tvResultIMC = findViewById(R.id.tvResultIMC)
        tvIMC = findViewById(R.id.tvIMC)
        tvIMCDescription = findViewById(R.id.tvIMCDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)

    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}