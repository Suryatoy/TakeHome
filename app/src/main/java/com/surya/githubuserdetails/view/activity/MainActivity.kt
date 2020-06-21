package com.surya.githubuserdetails.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior

import com.surya.githubuserdetails.R
import com.surya.githubuserdetails.model.repository.RepositoriesItem
import com.surya.githubuserdetails.model.user.User
import com.surya.githubuserdetails.util.hideSoftKeyBoard
import com.surya.githubuserdetails.util.loadImage
import com.surya.githubuserdetails.util.showError
import com.surya.githubuserdetails.view.adapter.RepositoriesAdapter
import com.surya.githubuserdetails.view.fragment.BottomSheetFragment
import com.surya.githubuserdetails.viewmodel.RepositoryViewModel
import com.surya.githubuserdetails.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), RepositoriesAdapter.OnItemClickListener {

    private lateinit var userViewModel: UserViewModel
    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var repositoryAdapter: RepositoriesAdapter
    private var bottomSheetFragment: BottomSheetFragment? = null

    private val userDataObserver = Observer<User> { user ->
        user?.let {
            user_details.visibility = View.VISIBLE
            user_name.text = user.name
            loadImage(user.avatar_url, user_avatar, this@MainActivity)
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        if (isError) {
           showError(user_search_layout, this@MainActivity)
        }
    }

    private val repositoryObserver = Observer<ArrayList<RepositoriesItem>> { repository ->
        repository?.let {
            repositories_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            repositoryAdapter = RepositoriesAdapter(repository, this@MainActivity)
            repositories_recyclerview.adapter = repositoryAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)

        search_button.setOnClickListener {
            search_edit_text.text?.let {
                userViewModel.user.observe(this@MainActivity, userDataObserver)
                userViewModel.getUserDetails(search_edit_text.text?.toString())
                repositoryViewModel.repositories.observe(this@MainActivity, repositoryObserver)
                userViewModel.loadError.observe(this, errorLiveDataObserver)
                repositoryViewModel.loadError.observe(this, errorLiveDataObserver)
                repositoryViewModel.getRepositories(search_edit_text.text?.toString())
                hideSoftKeyBoard(this, search_edit_text)
            }
        }
        var bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    /**
     * To show pop up of repository details
     */
    private fun showBottomSheetDialogFragment(repositoriesItem: RepositoriesItem) {
        if (bottomSheetFragment == null) {
            val bottomSheetFragment = BottomSheetFragment.newInstance(repositoriesItem)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        } else {
            bottomSheetFragment?.updateContent(repositoriesItem)
        }
    }

    /**
     * Click listener for Recyclerview
     */
    override fun onItemClicked(repositoriesItem: RepositoriesItem) {
        showBottomSheetDialogFragment(repositoriesItem)
    }
}
