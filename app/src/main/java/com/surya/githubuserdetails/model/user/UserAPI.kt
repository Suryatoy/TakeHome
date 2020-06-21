package com.surya.githubuserdetails.model.user

import com.surya.githubuserdetails.model.user.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {

    @GET("users/{userId}")
    fun getUserInfo(@Path("userId") userId: String?): Single<User>
}