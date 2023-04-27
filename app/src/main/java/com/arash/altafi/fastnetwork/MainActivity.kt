package com.arash.altafi.fastnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.fastnetwork.databinding.ActivityMainBinding
import com.arash.altafi.fastnetwork.fastNetwork.FastNetworkActivity
import com.arash.altafi.fastnetwork.okHttp.OkHttpActivity
import com.arash.altafi.fastnetwork.retrofit.RetrofitActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        btnFastNetwork.setOnClickListener {
            startActivity(Intent(this@MainActivity, FastNetworkActivity::class.java))
        }

        btnOkHttp.setOnClickListener {
            startActivity(Intent(this@MainActivity, OkHttpActivity::class.java))
        }

        btnRetrofit.setOnClickListener {
            startActivity(Intent(this@MainActivity, RetrofitActivity::class.java))
        }
    }

}