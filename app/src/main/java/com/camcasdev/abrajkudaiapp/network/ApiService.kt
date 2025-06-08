package com.camcasdev.abrajkudaiapp.network

import com.camcasdev.abrajkudaiapp.models.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Query

interface ApiService {

    @GET("admin/user/get/list")
    suspend fun getUserList(): List<User>

    @GET("admin/user/get/{userId}")
    suspend fun getUserById(@Path("userId") userIdParam: String): User;

    /*@PUT()*/


}