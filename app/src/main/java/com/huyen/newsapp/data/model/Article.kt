package com.huyen.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Article(

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("url")
    val url: String,

    @SerializedName("publishedAt")
    val publishedAt: String
)