package com.example.goodday.newsInfoModel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class NewsSource (
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

    companion object CREATOR : Parcelable.Creator<NewsSource> {
        override fun createFromParcel(parcel: Parcel): NewsSource {
            return NewsSource(parcel)
        }

        override fun newArray(size: Int): Array<NewsSource?> {
            return arrayOfNulls(size)
        }
    }
}
