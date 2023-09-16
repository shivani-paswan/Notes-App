package com.example.notesapp.model

data class NotesResponseModel(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)