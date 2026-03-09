package com.huyen.newsapp.data.remote

import com.huyen.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("api/v4/top-headlines")
    suspend fun getTopNews(

        @Query("lang")
        lang: String = "en",

        @Query("max")
        max: Int = 20,

        @Query("token")
        token: String

    ): NewsResponse


    @GET("api/v4/search")
    suspend fun searchNews(

        @Query("q")
        query: String,

        @Query("lang")
        lang: String = "en",

        @Query("token")
        token: String

    ): NewsResponse
}