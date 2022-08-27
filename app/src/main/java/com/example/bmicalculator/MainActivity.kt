package com.example.bmicalculator

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText : EditText = findViewById(R.id.etWeight)
        val heightText : EditText = findViewById(R.id.etHight)
        val calcBtn : Button = findViewById(R.id.calculate_btn)

        calcBtn.setOnClickListener {

            var weight = weightText.text.toString()
            var height = heightText.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / (height.toFloat() / 100 * height.toFloat() / 100)
                // Get result with two decimal places
                val bmiToDigit = String.format("%.2f", bmi).toFloat()
                displayResult(bmiToDigit)
            }

        }

    }

    private fun validateInput(weight:String?, height:String?) : Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your weight", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your height", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi : Float) {
        val resultIndex : TextView = findViewById(R.id.tvResult_bmi)
        val resultDesc : TextView = findViewById(R.id.tvResult_status)

        resultIndex.text = bmi.toString()
        var resultText : String = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultDesc.text = "Underweight"
                color = R.color.underweight_color
            }
            bmi in 18.50..24.99 -> {
                resultDesc.text = "Normal"
                color = R.color.normal_color
            }
            bmi in 25.00..29.99 -> {
                resultDesc.text = "Overweight"
                color = R.color.overweight_color
            }
            bmi > 29.99 -> {
                resultDesc.text = "Obese"
                color = R.color.obese_color
            }
            else -> {
                resultDesc.text = "This is else"
            }
        }
        resultDesc.setTextColor(ContextCompat.getColor(this, color))
    }

}