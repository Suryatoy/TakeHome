package com.surya.githubuserdetails.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.surya.githubuserdetails.R
import com.surya.githubuserdetails.model.repository.RepositoriesItem
import com.surya.githubuserdetails.util.convertTimestamp
import kotlinx.android.synthetic.main.bottom_sheet.*

class BottomSheetFragment() : BottomSheetDialogFragment() {

    companion object {
       private const val repositoryKey = "repositories"
        fun newInstance(repositoriesItem: RepositoriesItem) = BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putParcelable(repositoryKey, repositoriesItem)
            }
        }
    }

    private var fragmentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.bottom_sheet, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val repositoriesItem = arguments?.getParcelable<RepositoriesItem>(repositoryKey)
            repositoriesItem?.let {
                updateContent(it) }
        }
    }

    fun updateContent(repositoriesItem: RepositoriesItem) {
        last_updated_value.text = convertTimestamp(repositoriesItem.updated_at)
        stars_value.text = repositoriesItem.stargazers_count
        forks_value.text = repositoriesItem.forks
    }
}