package com.example.basicpaging3sampleappviews.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.basicpaging3sampleappviews.data.Article
import com.example.basicpaging3sampleappviews.data.repo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 50

class GetArticlesUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            enablePlaceholders = false
        )
    ) {
        repository.articlePagingSource()
    }.flow.flowOn(Dispatchers.IO)

    fun items(
        coroutineScope: CoroutineScope,
        op: (Throwable) -> Unit
    ): SharedFlow<PagingData<Article>> = this().cachedIn(coroutineScope).catch { op(it) }.shareIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(),
        replay = 1
    )

}