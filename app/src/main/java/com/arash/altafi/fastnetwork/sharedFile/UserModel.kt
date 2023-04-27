package com.arash.altafi.fastnetwork.sharedFile

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("Response")
    val response: List<UserList>
)

data class UserList(
    val id: String,
    val name: String,
    val avatar: String,
    val family: String
)
