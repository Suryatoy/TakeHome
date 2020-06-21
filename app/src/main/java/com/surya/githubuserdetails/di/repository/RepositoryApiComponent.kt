package com.surya.githubuserdetails.di.repository

import com.surya.githubuserdetails.model.repository.RepositoryApiService
import dagger.Component

@Component(modules = [RepositoryApiModule::class])
interface RepositoryApiComponent {

    fun inject(service: RepositoryApiService)

}