package com.surya.githubuserdetails.di.repository

import com.surya.githubuserdetails.model.repository.RepositoryAPI
import com.surya.githubuserdetails.model.repository.RepositoryApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class RepositoryApiModule {

    private val baseUrl = "https://api.github.com/"

    @Provides
    fun getRepositoryApi(): RepositoryAPI {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RepositoryAPI::class.java)
    }

    @Provides
   open fun getRepositoryApiService(): RepositoryApiService = RepositoryApiService()
}