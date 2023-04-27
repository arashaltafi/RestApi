package com.arash.altafi.fastnetwork.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.arash.altafi.fastnetwork.databinding.ActivityVolleyBinding
import com.arash.altafi.fastnetwork.sharedFile.Adapter
import com.arash.altafi.fastnetwork.sharedFile.Constance
import com.arash.altafi.fastnetwork.sharedFile.UserList
import com.arash.altafi.fastnetwork.sharedFile.toGone
import com.arash.altafi.fastnetwork.sharedFile.toast
import com.google.gson.Gson

class VolleyActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityVolleyBinding.inflate(layoutInflater)
    }

    private var adapter = Adapter()

    private val requestQueue by lazy {
        Volley.newRequestQueue(this)
    }

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        rvVolley.adapter = adapter

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, Constance.COMPLETE_URL, null,
            { response ->
                progressbar.toGone()

                val userList =
                    gson.fromJson(response.toString(), Array<UserList>::class.java).toList()
                adapter.submitList(userList)
                rvVolley.addItemDecoration(
                    DividerItemDecoration(
                        rvVolley.context,
                        RecyclerView.VERTICAL
                    )
                )
            },
            { error ->
                toast("Error Message: ${error.message}")
                progressbar.toGone()
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

}