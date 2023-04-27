package com.arash.altafi.fastnetwork.retrofit.api

import com.arash.altafi.fastnetwork.sharedFile.UserList
import com.arash.altafi.fastnetwork.sharedFile.UserModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("test_paging/test.php")
    suspend fun getUsers(): List<UserList>

}