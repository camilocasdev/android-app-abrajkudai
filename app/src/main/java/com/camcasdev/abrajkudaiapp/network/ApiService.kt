package com.camcasdev.abrajkudaiapp.network

import com.camcasdev.abrajkudaiapp.models.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @POST("admin/user/create")
    suspend fun createUser(@Body user: User): Response<User>;

    @GET("admin/user/get/list")
    suspend fun getUserList(): Response<List<User>>

    @GET("admin/user/get/{userId}")
    suspend fun getUserById(@Path("userId") _id: String): Response<User>;

    @PUT("admin/user/update/{userId}")
    suspend fun updateUser(@Path("userId") _id: String, @Body user: User): Response<User>;

    @DELETE("admin/user/delete/{userId}")
    suspend fun deleteUser(@Path("userId") _id: String): Response<User>;

}