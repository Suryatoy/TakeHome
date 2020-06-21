package com.surya.githubuserdetails

import com.surya.githubuserdetails.di.repository.RepositoryApiModule
import com.surya.githubuserdetails.model.repository.RepositoryApiService

class RepositoryApiModuleTest(private val mockRepositoryApiService: RepositoryApiService) :
    RepositoryApiModule() {

    override fun getRepositoryApiService(): RepositoryApiService {
        return mockRepositoryApiService
    }
}
