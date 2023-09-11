package com.example.notesapp.api

import com.example.notesapp.model.UserRequest
import com.example.notesapp.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/login")
    suspend fun singIn(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("/register")
    suspend fun signUp(@Body userRequest: UserRequest): Response<UserResponse>
}