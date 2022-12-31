package com.example.goodday.videoModel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsVideo(
//    @SerializedName("source")
//    var source: VideoSource? = null,

//    @SerializedName("author")
//    var author: String? = null,
//
//    @SerializedName("title")
//    var title: String? = null,
//
    @SerializedName("user")
    var user: VideoUser? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("image")
    var urlToImage: String? = null,

    @SerializedName("video_files")
    var videoFile: ArrayList<VideoFiles>? = null
//
//    @SerializedName("content")
//    var content: String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
//        parcel.readParcelable(VideoSource::class.java.classLoader),
        parcel.readParcelable(VideoUser::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        arrayListOf<VideoFiles>().apply {
            parcel.readArrayList(VideoFiles::class.java.classLoader)
        }
//        parcel.readParcelableArray(com.example.goodday.videoModel.VideoFiles::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(author)
//        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
//        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsVideo> {
        override fun createFromParcel(parcel: Parcel): NewsVideo {
            return NewsVideo(parcel)
        }

        override fun newArray(size: Int): Array<NewsVideo?> {
            return arrayOfNulls(size)
        }
    }
}