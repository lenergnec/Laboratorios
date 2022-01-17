package com.example.laboratorios

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.laboratorios.databinding.ActivityAlertDialogBinding
import com.example.laboratorios.databinding.ActivityMainBinding

class AlertDialog : AppCompatActivity() {
    private lateinit var binding: ActivityAlertDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}