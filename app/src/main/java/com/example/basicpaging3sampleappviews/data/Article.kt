package com.example.basicpaging3sampleappviews.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val created: LocalDateTime
) {

    @RequiresApi(Build.VERSION_CODES.O)
    private val articleDateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    val createdText: String
        @RequiresApi(Build.VERSION_CODES.O)
        get() = articleDateFormatter.format(created)
}