package com.example.notesapp.retrofits

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.api.UserApi
import com.example.notesapp.model.UserRequest
import com.example.notesapp.model.UserResponse
import com.example.notesapp.utiles.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {
    private  val _userResponseLiveData=MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData:LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData
    suspend fun  registerUser(userRequest:UserRequest){
        val response=userApi.signUp(userRequest)
        Log.d(TAG,response.body().toString())

//        HandleResponse(response)

        if(response.isSuccessful && response.body() != null){
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else if( response.errorBody() != null){
//            val errorObj=JSONObject(response.errorBody()!!.charStream().readText())
//            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))
        }else{
            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))

        }
//        HandleResponse(response)
    }
    suspend fun  loginUser(userRequest:UserRequest){
//        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response=userApi.singIn(userRequest)
        Log.d(TAG,response.body().toString())
//        HandleResponse(response)

        if(response.isSuccessful && response.body() != null){
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else if( response.errorBody() != null){
            val errorObj=JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))
        }else{
            _userResponseLiveData.postValue(NetworkResult.Error("something went wrong"))

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