package com.example.basicpaging3sampleappviews.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.basicpaging3sampleappviews.data.Article
import com.example.basicpaging3sampleappviews.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 50

@HiltViewModel
class ListArticleViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase
) : ViewModel() {

    val items: Flow<PagingData<Article>>
        get() {
            return Pager(
                config = PagingConfig(
                    pageSize = ITEMS_PER_PAGE,
                    enablePlaceholders = false
                )
            ) {
                articlesUseCase()
            }.flow.cachedIn(viewModelScope)
        }
}