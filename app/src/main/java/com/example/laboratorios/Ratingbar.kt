package com.example.laboratorios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laboratorios.databinding.ActivityRatingbarBinding
import kotlinx.android.synthetic.main.activity_ratingbar.*

class Ratingbar : AppCompatActivity() {
    private lateinit var binding: ActivityRatingbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener(){
            binding.rateText.text = binding.myRatingBar.rating.toString()
        }


    }
}