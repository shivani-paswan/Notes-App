package com.example.notesapp.api

import com.example.notesapp.model.NotesRequestModel
import com.example.notesapp.model.NotesResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface NotesApi {
    @GET("/comments")
    suspend fun  getNotes():Response<List<NotesResponseModel>>

    @POST("/comments")
    suspend fun  createNote(@Body notesRequestModel: NotesRequestModel):Response<NotesResponseModel>

    @PUT("/comments/{nodeId}")
    suspend fun  updateNote(@Path("noteId") noteId:String,@Body notesRequestModel: NotesRequestModel)

    @GET("/comments/{noteId}")
    suspend fun  deleteNotes(noteId:String):Response<NotesResponseModel>


}