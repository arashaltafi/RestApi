package com.arash.altafi.fastnetwork.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.fastnetwork.databinding.ActivityRetrofitBinding
import com.arash.altafi.fastnetwork.sharedFile.Adapter
import com.arash.altafi.fastnetwork.retrofit.api.ApiClient
import com.arash.altafi.fastnetwork.retrofit.api.ApiService
import com.arash.altafi.fastnetwork.sharedFile.toGone
import com.arash.altafi.fastnetwork.sharedFile.toast
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class RetrofitActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRetrofitBinding.inflate(layoutInflater)
    }

    private val api = ApiClient.instance.create(ApiService::class.java)

    private var adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MainScope().launch {
            init()
        }
    }

    private suspend fun init() = binding.apply {
        rvRetrofit.adapter = adapter

        try {
            val userModel = api.getUsers()
            progressbar.toGone()
            adapter.submitList(userModel)
            rvRetrofit.addItemDecoration(
                DividerItemDecoration(
                    rvRetrofit.context,
                    RecyclerView.VERTICAL
                )
            )
        } catch (e: Exception) {
            toast("Error Message: ${e.message}")
            progressbar.toGone()
        }

    }

}