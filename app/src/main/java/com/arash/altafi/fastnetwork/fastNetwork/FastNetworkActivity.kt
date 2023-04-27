package com.arash.altafi.fastnetwork.fastNetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.arash.altafi.fastnetwork.databinding.ActivityFastNetworkBinding
import com.arash.altafi.fastnetwork.sharedFile.Adapter
import com.arash.altafi.fastnetwork.sharedFile.Constance
import com.arash.altafi.fastnetwork.sharedFile.UserList
import com.arash.altafi.fastnetwork.sharedFile.toGone
import com.arash.altafi.fastnetwork.sharedFile.toast
import com.google.gson.Gson
import org.json.JSONArray

class FastNetworkActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFastNetworkBinding.inflate(layoutInflater)
    }

    private var adapter = Adapter()

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        rvFastNetwork.adapter = adapter

        AndroidNetworking.initialize(this@FastNetworkActivity)
        AndroidNetworking.get(Constance.COMPLETE_URL)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    val userList =
                        gson.fromJson(response.toString(), Array<UserList>::class.java).toList()
                    adapter.submitList(userList)

                    rvFastNetwork.addItemDecoration(
                        DividerItemDecoration(
                            rvFastNetwork.context,
                            RecyclerView.VERTICAL
                        )
                    )
                    progressbar.toGone()
                }

                override fun onError(anError: ANError) {
                    toast("Error Message: ${anError.message}")
                    progressbar.toGone()
                }

            })

    }

}