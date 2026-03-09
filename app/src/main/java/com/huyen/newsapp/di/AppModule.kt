package com.huyen.newsapp.di

import android.content.Context
import androidx.room.Room
import com.huyen.newsapp.data.local.ArticleDao
import com.huyen.newsapp.data.local.NewsDatabase
import com.huyen.newsapp.data.remote.NewsApi
import com.huyen.newsapp.data.remote.RetrofitInstance

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides

    @Singleton

    fun provideApi(): NewsApi =
        RetrofitInstance.api

    @Provides

    @Singleton

    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).build()

    @Provides

    fun provideDao(
        db: NewsDatabase
    ): ArticleDao =
        db.articleDao()
}