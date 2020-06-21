package com.surya.githubuserdetails.di.user

import com.surya.githubuserdetails.model.user.UserAPI
import com.surya.githubuserdetails.model.user.UserApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class UserApiModule {
    private val baseUrl = "https://api.github.com/"

    @Provides
    fun provideAPiModule(): UserAPI {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }

    @Provides
    open fun getUserApiService() = UserApiService()
}