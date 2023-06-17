package com.example.basicpaging3sampleappviews.domain

import androidx.paging.PagingSource
import com.example.basicpaging3sampleappviews.data.Article
import com.example.basicpaging3sampleappviews.data.repo.Repository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): PagingSource<Int, Article> {
        return repository.articlePagingSource()
    }
}