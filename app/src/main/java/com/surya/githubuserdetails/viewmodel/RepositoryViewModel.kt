package com.surya.githubuserdetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surya.githubuserdetails.di.AppModule
import com.surya.githubuserdetails.di.repository.DaggerRepositoryViewModelComponent
import com.surya.githubuserdetails.model.repository.RepositoriesItem
import com.surya.githubuserdetails.model.repository.RepositoryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryViewModel() : ViewModel() {

    constructor(test: Boolean = true) : this() {
        injected = true
    }

    internal val repositories by lazy { MutableLiveData<ArrayList<RepositoriesItem>>() }
    internal val loadError by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var repositoryApiService: RepositoryApiService

    fun inject() {
        if (!injected) {
            DaggerRepositoryViewModelComponent.builder()
                .build()
                .inject(this)
        }
    }

    private var injected = false
    private val disposable = CompositeDisposable()

    fun getRepositories(userId: String?) {
        inject()
        getRepositoryDetails(userId)
    }

    /**
     * To get repository details of the user
     */
    private fun getRepositoryDetails(userId: String?) {
        disposable.add(
            repositoryApiService.getRepositoryDetails(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<RepositoriesItem>>() {
                    override fun onSuccess(list: ArrayList<RepositoriesItem>) {
                        repositories.value = list
                        loadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        repositories.value = null
                        loadError.value = true
                    }
                })
        )
    }

    /**
     * To avoid memory leaks
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}