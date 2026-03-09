package com.huyen.newsapp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyen.newsapp.data.local.ArticleEntity
import com.huyen.newsapp.data.model.Article
import com.huyen.newsapp.data.model.NewsResponse
import com.huyen.newsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

    private val repository: NewsRepository

) : ViewModel() {

    val newsState = MutableStateFlow<List<Article>>(emptyList())

    private val apiKey = "21bfb5cfd9a81ebe219fd228efccd8f6"

    fun loadNews() {

        viewModelScope.launch {

            try {

                val response = repository.getNews(apiKey)

                newsState.value = response
            } catch (e: Exception) {

                e.printStackTrace()

            }

        }
    }

    fun saveArticle(article: ArticleEntity) {

        viewModelScope.launch {

            repository.saveArticle(article)

        }
    }

    fun searchNews(query: String) {

        viewModelScope.launch {

            try {

                val response = repository.searchNews(query, apiKey)

                newsState.value = response.articles

            } catch (e: Exception) {

                e.printStackTrace()

            }

        }
    }
}