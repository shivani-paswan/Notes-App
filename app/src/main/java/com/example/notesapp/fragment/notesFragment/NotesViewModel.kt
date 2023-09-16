package com.example.notesapp.fragment.notesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository) :ViewModel() {

    fun getNotes(){
        viewModelScope.launch {
            notesRepository.getNotes()
        }
    }
}