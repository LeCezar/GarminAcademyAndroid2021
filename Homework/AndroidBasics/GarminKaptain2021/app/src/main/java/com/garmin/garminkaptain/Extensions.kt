package com.garmin.garminkaptain

import android.content.Context
import android.text.format.DateFormat
import java.util.*

inline val <reified T> T.TAG: String
    get() = T::class.java.simpleName

fun Date.formatMmDdYy(context: Context): String {
    return DateFormat.getMediumDateFormat(context).format(this)
}
