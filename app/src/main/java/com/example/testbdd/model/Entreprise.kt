package com.example.testbdd.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

//@Parcelize
@SuppressLint("ParcelCreator")
data class Entreprise(
    val id: Int,
    val nom: String
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}