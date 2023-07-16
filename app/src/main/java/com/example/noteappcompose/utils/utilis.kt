package com.example.noteappcompose.utils

import java.text.SimpleDateFormat
import java.util.*

fun fromDate(time:Long) :String{
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
    return format.format(date)

}