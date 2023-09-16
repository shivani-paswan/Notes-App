package com.example.notesapp.di

import com.example.notesapp.api.AuthInterceptor
import com.example.notesapp.api.NotesApi
import com.example.notesapp.api.UserApi
import com.example.notesapp.utiles.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NotesModules {
    @Singleton
    @Provides
//    fun  providesRetrofitBuilder():Retrofit.Builder{
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//
//    }
     //// follow th e good practice for send okhttp client

    fun  providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    @Singleton
    @Provides
     fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
         return  OkHttpClient.Builder().addInterceptor(authInterceptor).build()
     }
    @Singleton
    @Provides
//    fun providesUserApi(retrofitBuilder: Retrofit.Builder): UserApi {
//        return retrofitBuilder.build().create(UserApi ::class.java)
//    }

    fun providesUserApi(retrofit: Retrofit): UserApi {
        return  retrofit.create(UserApi ::class.java)
    }

    fun  providesAuthRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }
//    @Singleton
//    @Provides
//    fun providesNotesApi(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):NotesApi{
//        return retrofitBuilder
//            .build()
//            .create(NotesApi::class.java)
//    }
}