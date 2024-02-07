package com.example.bankexercise.service

import com.example.bankexercise.data.Account
import com.example.bankexercise.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface UserApi {

    // TODO Setup Auth Header to encode username:password instead of passing as a path
    @POST("/user/{id}/password/{password}")
    suspend fun login(
        @Path("id") username: String,
        @Path("password") password: String
    ): Response<User>

    @GET("user/logout/{id}")
    suspend fun logout(
        @Path("id") userId: String
    ): Response<Boolean>

    @POST("user/{id}")
    suspend fun updateUser(
        @Path("id") userId: String,
        @Query("name") name: String
    ): Response<User>

    @GET("user/{id}/accounts")
    suspend fun getUserAccounts(
        @Path("id") userId: String
    ): Response<List<Account>>
}