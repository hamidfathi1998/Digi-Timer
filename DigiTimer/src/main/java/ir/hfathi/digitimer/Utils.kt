package ir.hfathi.digitimer

import java.text.SimpleDateFormat
import java.util.*

fun getTime(): Long {
    val c = Calendar.getInstance()
    val hour = c.get(Calendar.HOUR)
    val minute = c.get(Calendar.MINUTE)
    val seconds = c.get(Calendar.SECOND)
    val mil = c.get(Calendar.MILLISECOND)
    return (86400000 + (hour * 60 * 60 * 1000) + (minute * 60 * 1000) + (seconds * 1000) + mil).toLong()
}

fun convertMilToTimerFormat(dateTimeFormat:String,millisecond: Long): String {
    val dateFormat = SimpleDateFormat(dateTimeFormat, Locale.ROOT)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return (dateFormat.format(millisecond)).toString()
}
fun getZeroWithTimerFormat(dateTimeFormat:String): String {
    val dateFormat = SimpleDateFormat(dateTimeFormat, Locale.ROOT)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return (dateFormat.format(0)).toString()
}