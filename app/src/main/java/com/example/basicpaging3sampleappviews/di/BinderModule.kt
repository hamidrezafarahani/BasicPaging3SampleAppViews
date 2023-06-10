package com.example.basicpaging3sampleappviews.di

import com.example.basicpaging3sampleappviews.data.repo.ArticleRepository
import com.example.basicpaging3sampleappviews.data.repo.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {

    @Binds
    abstract fun bindRepository(repo: ArticleRepository): Repository
}