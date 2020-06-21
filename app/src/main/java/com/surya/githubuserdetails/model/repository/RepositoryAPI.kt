package com.surya.githubuserdetails.model.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryAPI {

    @GET("users/{userId}/repos")
    fun getRepositoryDetails(@Path("userId") userId: String?): Single<ArrayList<RepositoriesItem>>
}