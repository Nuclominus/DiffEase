package com.nuclominus.diffadapter.sample.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_FORMAT = "EEEE dd, MMM 'at' HH:mm"

    fun Date.toFormatDate(): String {
        return SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(this)
    }
}