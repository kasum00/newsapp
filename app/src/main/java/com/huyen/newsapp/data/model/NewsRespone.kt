package com.huyen.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.huyen.newsapp.data.model.Article

data class NewsResponse(

    val status: String,

    val totalResults: Int,

    val articles: List<Article>
)

