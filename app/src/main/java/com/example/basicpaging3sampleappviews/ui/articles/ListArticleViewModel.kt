package com.example.basicpaging3sampleappviews.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.basicpaging3sampleappviews.data.Article
import com.example.basicpaging3sampleappviews.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListArticleViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase
) : ViewModel() {

    val items: SharedFlow<PagingData<Article>>
        get() = articlesUseCase().cachedIn(viewModelScope).catch {
            Timber.tag(this@ListArticleViewModel::class.java.simpleName).d(it)
        }.shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )

    // OR USING THIS

    val articles: SharedFlow<PagingData<Article>>
        get() = articlesUseCase.items(viewModelScope) {
            Timber.tag(this@ListArticleViewModel::class.java.simpleName).d(it)
        }
}