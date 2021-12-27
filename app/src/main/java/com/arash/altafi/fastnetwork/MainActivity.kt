package com.arash.altafi.fastnetwork

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.activity_main.*

// more sample in => https://github.com/amitshekhariitbhu/Fast-Android-Networking

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        AndroidNetworking.initialize(this)
        //api for mock data (json array)
        AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getAsObjectList(User::class.java, object : ParsedRequestListener<List<User>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(users: List<User>) {
                    adapter = MainAdapter(arrayListOf())
                    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, RecyclerView.VERTICAL))
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter.addData(users)
                    adapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(this@MainActivity, "خطا در اتصال", Toast.LENGTH_LONG).show()
                }

            })
    }

}