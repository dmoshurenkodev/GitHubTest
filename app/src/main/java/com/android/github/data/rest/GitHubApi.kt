package com.android.github.data.rest

import com.android.github.data.models.UserDetailModel
import com.android.github.data.models.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("/users")
    fun loadUsers(@Query("since") lastUserId: String, @Query("per_page") perPage: String): Call<List<UserModel>>

    @GET("/users/{login}")
    fun loadUserDetail(@Path("login") login: String): Call<UserDetailModel>
}