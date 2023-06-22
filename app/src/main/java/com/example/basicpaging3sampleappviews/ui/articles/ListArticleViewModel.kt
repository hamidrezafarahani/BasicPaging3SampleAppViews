package com.example.basicpaging3sampleappviews.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.basicpaging3sampleappviews.data.Article
import com.example.basicpaging3sampleappviews.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListArticleViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _items = MutableStateFlow<PagingData<Article>>(PagingData.empty())

    val items: StateFlow<PagingData<Article>> get() = _items

    init {

        viewModelScope.launch {
            articlesUseCase().cachedIn(viewModelScope)
                .onEach {
                    _items.value = it
                }.catch {
                    Timber.tag(this@ListArticleViewModel::class.java.simpleName).d(it)
                }.collect()
        }
    }
}