package com.surya.githubuserdetails.model.repository

import android.os.Parcel
import android.os.Parcelable

data class RepositoriesItem(
    val description: String?,
    val forks: String?,
    val name: String?,
    val stargazers_count: String?,
    val updated_at: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(forks)
        parcel.writeString(name)
        parcel.writeString(stargazers_count)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoriesItem> {
        override fun createFromParcel(parcel: Parcel): RepositoriesItem {
            return RepositoriesItem(
                parcel
            )
        }

        override fun newArray(size: Int): Array<RepositoriesItem?> {
            return arrayOfNulls(size)
        }
    }
}
