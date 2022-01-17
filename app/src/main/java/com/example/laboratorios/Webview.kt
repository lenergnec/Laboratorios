package com.example.laboratorios

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.laboratorios.databinding.ActivityWebviewBinding

class Webview : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myBrowser: WebView = binding.myWeb
        myBrowser.webViewClient = WebViewClient()

        //show the url
        myBrowser.loadUrl("https://www.google.com")
        //set the webview to have  transparent border
        myBrowser.setBackgroundColor(Color.TRANSPARENT)
        //To enable JavaScript for the web browser
        myBrowser.settings.javaScriptCanOpenWindowsAutomatically = true
        //to load images automatically
        myBrowser.settings.loadsImagesAutomatically = true
        //enable scrolling
        myBrowser.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    }
}