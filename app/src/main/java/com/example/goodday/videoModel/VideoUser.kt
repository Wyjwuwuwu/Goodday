package com.example.goodday.videoModel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class VideoUser(
    var id: String? = null,

    @SerializedName("name")
    var description: String? = null,

    @SerializedName("url")
    var url: String? = null,

    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoUser> {
        override fun createFromParcel(parcel: Parcel): VideoUser {
            return VideoUser(parcel)
        }

        override fun newArray(size: Int): Array<VideoUser?> {
            return arrayOfNulls(size)
        }
    }
}