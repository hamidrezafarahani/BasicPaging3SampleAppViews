package com.example.basicpaging3sampleappviews.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.basicpaging3sampleappviews.data.Article
import com.example.basicpaging3sampleappviews.data.repo.Repository
import com.example.basicpaging3sampleappviews.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 50

@HiltViewModel
class ListArticleViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase
) : ViewModel() {

    val items: Flow<PagingData<Article>>
        get() {
            val config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false)
            val pagingSourceFactory = { articlesUseCase() }
            return Pager(config) {
                pagingSourceFactory()
            }.flow.cachedIn(viewModelScope)
        }
}