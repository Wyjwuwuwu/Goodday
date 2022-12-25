package com.example.goodday.videoModel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class VideoSource (
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoSource> {
        override fun createFromParcel(parcel: Parcel): VideoSource {
            return VideoSource(parcel)
        }

        override fun newArray(size: Int): Array<VideoSource?> {
            return arrayOfNulls(size)
        }
    }
}
