package com.surya.githubuserdetails.model.user


import com.surya.githubuserdetails.di.user.DaggerUserApiComponent
import com.surya.githubuserdetails.model.user.User
import com.surya.githubuserdetails.model.user.UserAPI
import io.reactivex.Single
import javax.inject.Inject

class UserApiService {

    @Inject
    lateinit var api: UserAPI

    init {
        DaggerUserApiComponent.create().inject(this)
    }

    fun getUserDetails(userId: String?): Single<User> {
        return api.getUserInfo(userId)
    }
}