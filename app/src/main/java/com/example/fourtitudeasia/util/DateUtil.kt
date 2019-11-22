package com.example.fourtitudeasia.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun dateId(): String {
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm:ss")
        val format = sdf.format(Calendar.getInstance().getTime())
        return format
    }
}