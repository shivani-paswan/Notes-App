package com.example.notesapp.di

import com.example.notesapp.api.UserApi
import com.example.notesapp.utiles.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NotesModules {
    @Singleton
    @Provides
    fun  providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    fun providesUserApi(retrofit: Retrofit): UserApi {
        return  retrofit.create(UserApi ::class.java)
    }
}