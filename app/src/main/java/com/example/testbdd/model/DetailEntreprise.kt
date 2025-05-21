package com.example.testbdd.model

import android.os.Parcel
import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

//@Parcelize
data class DetailEntreprise(
    val id: Int = 0,
    val id_candidats: Int,
    val id_entreprises: Int,
    val checkbox: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(id_candidats)
        parcel.writeInt(id_entreprises)
        parcel.writeByte(if (checkbox) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailEntreprise> {
        override fun createFromParcel(parcel: Parcel): DetailEntreprise {
            return DetailEntreprise(parcel)
        }

        override fun newArray(size: Int): Array<DetailEntreprise?> {
            return arrayOfNulls(size)
        }
    }

}