package com.example.testbdd.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

//@Parcelize
@SuppressLint("ParcelCreator")
data class Disponibilite(
    val id: Int,
    val id_entreprises: Int,
    val date: String,
    val heure_debut: String,
    val heure_fin: String
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}