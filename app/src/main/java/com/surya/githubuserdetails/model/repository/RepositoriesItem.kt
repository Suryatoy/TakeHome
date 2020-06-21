package com.surya.githubuserdetails.model.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoriesItem(
    val description: String?,
    val forks: String?,
    val name: String?,
    val stargazers_count: String?,
    val updated_at: String?
) : Parcelable

