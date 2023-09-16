package com.example.notesapp.fragment.notesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.api.NotesApi
import com.example.notesapp.model.NotesRequestModel
import com.example.notesapp.model.NotesResponseModel
import com.example.notesapp.utiles.NetworkResult
import javax.inject.Inject

class NotesRepository @Inject constructor(private  val notesApi:NotesApi){
    private val _notesMuteLiveData=MutableLiveData<NetworkResult<List<NotesResponseModel>>>()

    private val  statusMsgLiveData=MutableLiveData<NetworkResult<String>>()

    val notesLiveData:LiveData<NetworkResult<List<NotesResponseModel>>>
        get() = _notesMuteLiveData

    suspend fun  getNotes(){
        _notesMuteLiveData.postValue(NetworkResult.Loading())
        val response=notesApi.getNotes()

        if(response.isSuccessful && response.body() != null){
            _notesMuteLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else if( response.errorBody() != null){
//            val errorObj=JSONObject(response.errorBody()!!.charStream().readText())
//            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            _notesMuteLiveData.postValue(NetworkResult.Error("something went wrong"))
        }else{
            _notesMuteLiveData.postValue(NetworkResult.Error("something went wrong"))

        }
    }

    suspend fun createNotes(notesRequestModel: NotesRequestModel){
        statusMsgLiveData.postValue(NetworkResult.Loading())
        val response=notesApi.createNote(notesRequestModel)
         if(response.isSuccessful && response.body() !=null){
             statusMsgLiveData.postValue(NetworkResult.Success("Note created"))
         }else{
             statusMsgLiveData.postValue(NetworkResult.Error(" something went  wrong"))
         }

    }

   suspend fun updateNotes(noteId: String,notesRequestModel: NotesRequestModel){
       statusMsgLiveData.postValue(NetworkResult.Loading())
       val response=notesApi.updateNote(noteId,notesRequestModel)

   }

    suspend fun deleteNotes(noteId:String){
        statusMsgLiveData.postValue(NetworkResult.Loading())
        val response=notesApi.deleteNotes(noteId)
        if(response.isSuccessful && response.body() !=null){
            statusMsgLiveData.postValue(NetworkResult.Success("Note created"))
        }else{
            statusMsgLiveData.postValue(NetworkResult.Error(" something went  wrong"))
        }
    }


}