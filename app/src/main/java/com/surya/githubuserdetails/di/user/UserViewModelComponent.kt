package com.surya.githubuserdetails.di.user

import com.surya.githubuserdetails.di.AppModule
import com.surya.githubuserdetails.di.user.UserApiModule
import com.surya.githubuserdetails.viewmodel.UserViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserApiModule::class,AppModule::class])
interface UserViewModelComponent {

    fun inject(userViewModel: UserViewModel)
}