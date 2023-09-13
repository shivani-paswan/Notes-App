package com.example.notesapp

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.LoginRequest
import com.example.notesapp.model.LoginResponse
import com.example.notesapp.model.UserRequest
import com.example.notesapp.model.UserResponse
import com.example.notesapp.retrofits.UserRepository
import com.example.notesapp.utiles.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private  val userRepository: UserRepository) :ViewModel(){
 val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
     get() = userRepository.userResponseLiveData
    val userLoginLiveData: LiveData<NetworkResult<LoginResponse>>
     get() = userRepository.userLoginResponseLiveData
    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.registerUser(userRequest)

        }

    }
     fun loginUser(loginRequest: LoginRequest){
         viewModelScope.launch {
             userRepository.loginUser(loginRequest)

         }

     }

     fun  validateCredential(username:String,emailAddress:String,pasword:String):Pair<Boolean,String>{
         var result =Pair(true,"")
             if(TextUtils.isEmpty(username)||TextUtils.isEmpty(emailAddress)||TextUtils.isEmpty(pasword)){
                 result=Pair(false,"please provide data")
             } else if( Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                 result=Pair(false,"please provide valid  data")
             }
         else if (pasword.length <= 10){
                 result=Pair(false,"incorrect password")

             }
         return  result
     }









}