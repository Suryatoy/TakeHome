package com.surya.githubuserdetails.di.repository

import com.surya.githubuserdetails.di.AppModule
import com.surya.githubuserdetails.viewmodel.RepositoryViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryApiModule::class,AppModule::class])
interface RepositoryViewModelComponent {

    fun inject(repositoryViewModel: RepositoryViewModel)
}