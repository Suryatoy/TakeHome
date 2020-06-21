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
import com.surya.githubuserdetails.util.GenericUtils
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

    private val userDataObserver = Observer<User> { list ->
        list?.let {
            user_details.visibility = View.VISIBLE
            user_name.text = list.name
            GenericUtils.loadImage(list.avatar_url, user_avatar, this@MainActivity)
        }
    }

    private val repositoryObserver = Observer<ArrayList<RepositoriesItem>> { list ->
        list?.let {
            repositories_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            repositoryAdapter = RepositoriesAdapter(list, this@MainActivity)
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
                repositoryViewModel.getRepositories(search_edit_text.text?.toString())
            }
        }
        var bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun showBottomSheetDialogFragment(repositoriesItem: RepositoriesItem) {
        if (bottomSheetFragment == null) {
            val bottomSheetFragment = BottomSheetFragment.newInstance(repositoriesItem)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        } else {
            bottomSheetFragment?.updateContent(repositoriesItem)
        }
    }

    override fun onItemClicked(repositoriesItem: RepositoriesItem) {
        showBottomSheetDialogFragment(repositoriesItem)
    }
}