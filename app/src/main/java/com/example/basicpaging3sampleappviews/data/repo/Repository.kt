package com.example.basicpaging3sampleappviews.data.repo

import androidx.paging.PagingSource
import com.example.basicpaging3sampleappviews.data.Article

interface Repository {
    fun articlePagingSource(): PagingSource<Int, Article>
}