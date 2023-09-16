package com.example.notesapp.utiles

import android.content.Context
import com.example.notesapp.utiles.Constants.PREF_TOKEN_FILE
import com.example.notesapp.utiles.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context:Context) {

    private var pref=context.getSharedPreferences(PREF_TOKEN_FILE,Context.MODE_PRIVATE)

    fun saveToken(token:String){
        val editor=pref.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun getToken(): String? {
        return  pref.getString(USER_TOKEN,null)
    }
}