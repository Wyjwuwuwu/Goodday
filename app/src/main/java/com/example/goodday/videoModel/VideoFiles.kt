package com.example.goodday.videoModel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class VideoFiles(
    var id: String? = null,

    @SerializedName("quality")
    var quality: String? = null,

    @SerializedName("link")
    var url: String? = null

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(quality)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoFiles> {
        override fun createFromParcel(parcel: Parcel): VideoFiles {
            return VideoFiles(parcel)
        }

        override fun newArray(size: Int): Array<VideoFiles?> {
            return arrayOfNulls(size)
        }
    }
}