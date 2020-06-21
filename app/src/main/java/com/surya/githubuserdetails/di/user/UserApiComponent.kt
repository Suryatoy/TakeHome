package com.surya.githubuserdetails.di.user

import com.surya.githubuserdetails.model.user.UserApiService
import dagger.Component

@Component(modules = [UserApiModule::class])
interface UserApiComponent {

    fun inject(service: UserApiService)
}