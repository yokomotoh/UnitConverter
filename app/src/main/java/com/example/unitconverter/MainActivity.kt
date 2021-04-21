package com.example.unitconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconverter.databinding.ActivityMainBinding
import java.text.NumberFormat


/*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.buttonCalculate.setOnClickListener{ calculateUnit() }
    }

    private fun calculateUnit() {
        val stringTextField = binding.enterNum.text.toString()
        val numToConvert = stringTextField.toDoubleOrNull()
        if (numToConvert == null || numToConvert == 0.0) {
            displayNumConverted(0.0)
            return
        }
        val unitConvertRate = when (binding.chooseUnit.checkedRadioButtonId) {
            R.id.fluidounces_to_milliliters -> 29.5735
            R.id.milliliters_to_fluidouces -> 0.0351951
            R.id.cups_to_grams -> 125.0
            R.id.grams_to_cups -> 0.01
            else -> 0.0
        }
        var numConverted = unitConvertRate * numToConvert
        if (binding.switchRoundUp.isChecked) {
            numConverted = kotlin.math.ceil(numConverted)
        }

        displayNumConverted(numConverted)
    }

    private fun displayNumConverted(numConverted: Double) {
        val formattedNumConverted = NumberFormat.getInstance().format(numConverted)
        binding.resultCalculate.text = getString(R.string.result, formattedNumConverted)


    }
}