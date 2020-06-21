package com.surya.githubuserdetails.model.repository

import com.surya.githubuserdetails.di.repository.DaggerRepositoryApiComponent
import io.reactivex.Single
import javax.inject.Inject

class RepositoryApiService {

    @Inject
    lateinit var repositoryAPI: RepositoryAPI

    init {
        DaggerRepositoryApiComponent.create().inject(this)
    }

    fun getRepositoryDetails(userId: String?): Single<ArrayList<RepositoriesItem>> {
        return repositoryAPI.getRepositoryDetails(userId)
    }
}