 package com.example.notesapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.notesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

 @AndroidEntryPoint
 class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
//    lateinit var notesApi: NotesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
//        CoroutineScope(Dispatchers.IO).launch{
//            val response=notesApi.getNotes()
//            Log.d(TAG, response.body().toString())
//        }
    }


}