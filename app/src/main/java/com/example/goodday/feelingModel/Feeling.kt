package com.example.goodday.feelingModel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
class Feeling (

    var uid :String? = null,
    var temperature: Int = 0,
    var bloPressure: Int = 0,
    var shiver: Int = 0,
    var breath: Int = 0,
    var pulse: Int = 0,
    var date: String = ""
): Parcelable