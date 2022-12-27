package com.example.goodday

import java.util.*

fun main(){
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val date = "$year-${month + 1}-$day"
    print(date)
}