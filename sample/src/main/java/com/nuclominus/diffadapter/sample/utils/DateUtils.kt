package com.nuclominus.diffadapter.sample.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_FORMAT = "EEEE dd, MMM 'at' HH:mm"

    fun Date.toFormatDate(): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        return try {
            dateFormat.format(this)
        } catch (ex: Exception) {
            ""
        }
    }
}