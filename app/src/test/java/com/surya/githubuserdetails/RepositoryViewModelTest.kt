package com.surya.githubuserdetails

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.surya.githubuserdetails.di.AppModule
import com.surya.githubuserdetails.di.repository.DaggerRepositoryViewModelComponent
import com.surya.githubuserdetails.model.repository.RepositoriesItem
import com.surya.githubuserdetails.model.repository.RepositoryApiService
import com.surya.githubuserdetails.viewmodel.RepositoryViewModel
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

class RepositoryViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockRepositoryApiService: RepositoryApiService

    var repositoryViewModel = RepositoryViewModel( true)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerRepositoryViewModelComponent.builder()
            .repositoryApiModule(RepositoryApiModuleTest(mockRepositoryApiService))
            .build()
            .inject(repositoryViewModel)
    }

    @Test
    fun getRepositoryDetailsSuccess() {

        val repositoriesItem = RepositoriesItem(
            "My first repository on GitHub!",
            "1176",
            "Hello-World",
            "1421",
            "2017-08-14T08:08:10Z"
        )
        val repositories = (arrayListOf(repositoriesItem))
        val testSingle = Single.just((repositories))

        Mockito.`when`(mockRepositoryApiService.getRepositoryDetails("octocat"))
            .thenReturn(testSingle)

        repositoryViewModel.getRepositories("octocat")

        Assert.assertEquals("My first repository on GitHub!", repositoryViewModel.repositories.value?.get(0)?.description)
        Assert.assertEquals("1176",repositoryViewModel.repositories.value?.get(0)?.forks)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }

        }
        RxJavaPlugins.setIoSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

}