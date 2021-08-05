package com.example.bmicalculateapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import com.example.bmicalculateapplication.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // when user click on image boy
        binding.imageBoy.setOnClickListener {
            binding.imageBoy.setImageResource(R.drawable.ic_boy)
            binding.imageGirl.setImageResource(R.drawable.ic_girl_blur)
        }

        //when user click on image girl
        binding.imageGirl.setOnClickListener {
            binding.imageGirl.setImageResource(R.drawable.ic_girl)
            binding.imageBoy.setImageResource(R.drawable.ic_boy_blur)
        }

        //user input validation
        binding.calculateButton.setOnClickListener {
            var weight = 0.0
            var height = 0.0

            if (binding.weightValue.text.toString().isNotEmpty())
                weight = binding.weightValue.text.toString().toDouble()

            if (binding.heightValue.text.toString().isNotEmpty())
                height = binding.heightValue.text.toString().toDouble()

            // calculate IBM
            if (weight > 0.0 && height > 0.0) {
                val bmi = String.format("0.2%", weight / height.pow(2))
                binding.bmi.text = bmi
                binding.bmiStatus.text = bmiStatusValue(weight / height.pow(2))

                //change visibility after show result
                binding.bmiView.visibility = VISIBLE
                binding.calculateButton.visibility = GONE
            }else
                Toast.makeText(this,"Please input weight and height value greater than 0",Toast.LENGTH_LONG).show()
        }

        //click user on button calculate again and clear Edittext
        binding.calculateAgain.setOnClickListener{
            binding.bmiView.visibility = GONE
            binding.calculateButton.visibility = VISIBLE
            binding.weightValue.text.clear()
            binding.heightValue.text.clear()
            binding.weightValue.requestFocus()


        }
    }

    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "UnderWeight"
        if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        if (bmi >= 25 && bmi < 30)
            bmiStatus = "OverWeight"
        if (bmi >= 30)
            bmiStatus = "Obese"
        return bmiStatus
    }
}