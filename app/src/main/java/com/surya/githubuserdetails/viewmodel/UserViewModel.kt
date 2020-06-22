package com.surya.githubuserdetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surya.githubuserdetails.di.AppModule
import com.surya.githubuserdetails.di.user.DaggerUserViewModelComponent

import com.surya.githubuserdetails.model.user.User
import com.surya.githubuserdetails.model.user.UserApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UserViewModel() : ViewModel() {

    constructor( test: Boolean = true) : this() {
        injected = true
    }

    internal val user by lazy { MutableLiveData<User>() }
    internal val loadError by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var apiService: UserApiService
    private val disposable = CompositeDisposable()
    private var injected = false

    fun inject() {
        if (!injected) {
            DaggerUserViewModelComponent.builder()
                .build()
                .inject(this)
        }
    }

    fun getUserDetails(userId: String?) {
        inject()
        getUserInformation(userId)
    }

    /**
     * To get User details like User name and avatar url
     */
    private fun getUserInformation(userId: String?) {
        disposable.add(
            apiService.getUserDetails(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<User>() {
                    override fun onSuccess(list: User) {
                        user.value = list
                        loadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        user.value = null
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