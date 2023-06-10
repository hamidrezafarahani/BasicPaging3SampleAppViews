package com.example.basicpaging3sampleappviews.ui.articles

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.basicpaging3sampleappviews.data.Article

class ArticleViewModel(private val article: Article) {

    val title = article.title
    val description = article.description
    @RequiresApi(Build.VERSION_CODES.O)
    val createdAt = article.createdText
}