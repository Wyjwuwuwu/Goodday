package com.example.goodday

import android.os.Parcel
import android.os.Parcelable

data class VidImageModel(var VidImageName: String,
                         var VidImageImg: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(VidImageName)
        parcel.writeInt(VidImageImg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VidImageModel> {
        override fun createFromParcel(parcel: Parcel): VidImageModel {
            return VidImageModel(parcel)
        }

        override fun newArray(size: Int): Array<VidImageModel?> {
            return arrayOfNulls(size)
        }
    }
}
