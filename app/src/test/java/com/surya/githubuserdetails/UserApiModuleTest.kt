package com.surya.githubuserdetails

import com.surya.githubuserdetails.di.user.UserApiModule
import com.surya.githubuserdetails.model.user.UserApiService

class UserApiModuleTest(private val mockUserApiService:UserApiService): UserApiModule() {

    override fun getUserApiService(): UserApiService {
        return mockUserApiService
    }
}
