package com.example.basicpaging3sampleappviews.data.repo

import com.example.basicpaging3sampleappviews.data.ArticlePagingSource
import javax.inject.Inject

class ArticleRepository @Inject constructor(): Repository {

    fun articlePagingSource() = ArticlePagingSource()
}