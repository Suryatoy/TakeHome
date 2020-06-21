package com.surya.githubuserdetails.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.surya.githubuserdetails.R
import com.surya.githubuserdetails.model.repository.RepositoriesItem
import com.surya.githubuserdetails.util.GenericUtils
import kotlinx.android.synthetic.main.bottom_sheet.*

class BottomSheetFragment() : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(repositoriesItem: RepositoriesItem) = BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putParcelable("repositories", repositoriesItem)
            }
        }
    }

    private var fragmentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            val repositoriesItem = arguments?.getParcelable<RepositoriesItem>("repositories")
            repositoriesItem?.let {
                updateContent(it) }
        }
    }

    fun updateContent(repositoriesItem: RepositoriesItem) {
        last_updated_value.text = GenericUtils.convertTimestamp(repositoriesItem.updated_at)
        stars_value.text = repositoriesItem.stargazers_count
        forks_value.text = repositoriesItem.forks
    }
}