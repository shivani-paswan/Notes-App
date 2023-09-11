package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.UserRequest
import com.example.notesapp.model.UserResponse
import com.example.notesapp.retrofits.UserRepository
import com.example.notesapp.utiles.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private  val userRepository: UserRepository) :ViewModel(){
 val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
     get() = userRepository.userResponseLiveData
    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.registerUser(userRequest)

        }

    }
     fun loginUser(userRequest: UserRequest){
         viewModelScope.launch {
             userRepository.loginUser(userRequest)

         }

     }
}