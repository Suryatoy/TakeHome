package com.surya.githubuserdetails

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.surya.githubuserdetails.di.AppModule
import com.surya.githubuserdetails.di.user.DaggerUserViewModelComponent
import com.surya.githubuserdetails.model.user.User
import com.surya.githubuserdetails.model.user.UserApiService
import com.surya.githubuserdetails.viewmodel.UserViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor


class UserViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var userApiService: UserApiService

    val application = Mockito.mock(Application::class.java)

    var userViewModel = UserViewModel(application, true)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerUserViewModelComponent.builder()
            .appModule(AppModule(application))
            .userApiModule(UserApiModuleTest(userApiService))
            .build()
            .inject(userViewModel)
    }

    @Test
    fun getUserDetailsSuccess() {

        val user = User("https://avatars3.githubusercontent.com/u/583231?v=4", "The Octocat")

        val testSingle = Single.just(user)

        Mockito.`when`(userApiService.getUserDetails("octocat")).thenReturn(testSingle)

        userViewModel.getUserDetails("octocat")

        Assert.assertEquals("The Octocat", userViewModel.user.value?.name)
        Assert.assertEquals(
            "https://avatars3.githubusercontent.com/u/583231?v=4",
            userViewModel.user.value?.avatar_url
        )
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }

        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

}