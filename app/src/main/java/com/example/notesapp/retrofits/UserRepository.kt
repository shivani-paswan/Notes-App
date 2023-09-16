package com.example.notesapp.retrofits

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.api.UserApi
import com.example.notesapp.model.LoginRequest
import com.example.notesapp.model.LoginResponse
import com.example.notesapp.model.UserRequest
import com.example.notesapp.model.UserResponse
import com.example.notesapp.utiles.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {
    private  val _userResponseLiveData=MutableLiveData<NetworkResult<UserResponse>>()
    private  val _userLoginResponseLiveData=MutableLiveData<NetworkResult<LoginResponse>>()
    val userResponseLiveData:LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData
    val userLoginResponseLiveData:LiveData<NetworkResult<LoginResponse>>
        get() = _userLoginResponseLiveData
    suspend fun  registerUser(userRequest:UserRequest){
        val response=userApi.signUp(userRequest)
        Log.d(TAG,response.body().toString())

//        HandleResponse(response)

        if(response.isSuccessful && response.body() != null){
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else if( response.errorBody() != null){
//            val errorObj=JSONObject(response.errorBody()!!.charStream().readText())
//            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))
        }else{
            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))

        }
//        HandleResponse(response)
    }
    suspend fun  loginUser(loginRequest: LoginRequest){
//        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response=userApi.singIn(loginRequest)
        Log.d(TAG,response.body().toString())
//        HandleResponse(response)

        if(response.isSuccessful && response.body() != null){
            _userLoginResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else if( response.errorBody() != null){
            val errorObj=JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))
        }else{
            _userLoginResponseLiveData.postValue(NetworkResult.Error("something went wrong"))

        }

    }

//    private fun HandleResponse(response: Response<UserResponse>) {
//        if (response.isSuccessful && response.body() != null) {
//            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
//        } else if (response.errorBody() != null) {
//            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
//            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//        } else {
//            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))
//
//        }
//    }

}

