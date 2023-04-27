package com.arash.altafi.fastnetwork.okHttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.fastnetwork.databinding.ActivityOkHttpBinding
import com.arash.altafi.fastnetwork.sharedFile.Adapter
import com.arash.altafi.fastnetwork.sharedFile.Constance
import com.arash.altafi.fastnetwork.sharedFile.UserList
import com.arash.altafi.fastnetwork.sharedFile.toGone
import com.arash.altafi.fastnetwork.sharedFile.toast
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class OkHttpActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityOkHttpBinding.inflate(layoutInflater)
    }

    private val client = okhttp3.OkHttpClient()
    private val gson = Gson()

    private var adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        rvOkHttp.adapter = adapter

        val request = Request.Builder()
            .url(Constance.COMPLETE_URL)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val userList =
                    gson.fromJson(json, Array<UserList>::class.java).toList()

                runOnUiThread {
                    adapter.submitList(userList)

                    rvOkHttp.addItemDecoration(
                        DividerItemDecoration(
                            rvOkHttp.context,
                            RecyclerView.VERTICAL
                        )
                    )
                    progressbar.toGone()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    toast(e.message.toString())
                    progressbar.toGone()
                }
            }
        })
    }

}