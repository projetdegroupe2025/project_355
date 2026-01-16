package com.example.project_355.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

data class FormattedDate(
    val dayNum: Int,
    val monthNum: Int,
    val year: Int,
    val day: String,
    val month: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun formatISODate(isoDateString: String): FormattedDate {
    // Parse the ISO date string
    val dateTime = LocalDateTime.parse(isoDateString, DateTimeFormatter.ISO_DATE_TIME)
    val date = dateTime.toLocalDate()
    
    // Get today's date for comparison
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val tomorrow = today.plusDays(1)
    
    // Determine the day string (Today, Tomorrow, Yesterday, or day name)
    val dayString = when (date) {
        today -> "Today"
        yesterday -> "Yesterday"
        tomorrow -> "Tomorrow"
        else -> date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }
    
    // Get month name
    val monthName = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    
    return FormattedDate(
        dayNum = date.dayOfMonth,
        monthNum = date.monthValue,
        year = date.year,
        day = dayString,
        month = monthName
    )
}
