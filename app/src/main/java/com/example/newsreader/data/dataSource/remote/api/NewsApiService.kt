package com.example.newsreader.data.dataSource.remote.api


import com.example.newsreader.BuildConfig
import com.example.newsreader.data.dataSource.remote.response.NewsResponse
import com.example.newsreader.utils.NewsConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


 interface NewsApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") category: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

}