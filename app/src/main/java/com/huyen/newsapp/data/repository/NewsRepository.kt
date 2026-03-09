package com.huyen.newsapp.data.repository

import com.huyen.newsapp.data.local.ArticleDao
import com.huyen.newsapp.data.local.ArticleEntity
import com.huyen.newsapp.data.model.Article
import com.huyen.newsapp.data.remote.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NewsRepository @Inject constructor(

    private val api: NewsApi,

    private val dao: ArticleDao

) {

    suspend fun getNews(token: String): List<Article> {

        return try {

            val response = api.getTopNews(token = token)

            response.articles

        } catch (e: Exception) {

            dao.getBookmarks().first().map {

                Article(
                    it.title,
                    it.description,
                    it.image,
                    it.url,
                    it.publishedAt
                )
            }
        }
    }

    suspend fun searchNews(query: String, token: String) =
        api.searchNews(query = query, token = token)

    suspend fun saveArticle(article: ArticleEntity) =
        dao.insert(article)

    fun getBookmarks(): Flow<List<ArticleEntity>> =
        dao.getBookmarks()
}