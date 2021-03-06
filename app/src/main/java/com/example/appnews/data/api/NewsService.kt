package com.example.appnews.data.api

import com.example.appnews.data.model.NewsResponse
import com.example.appnews.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
      fun getBusinessNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("limit")
        loadSize: Int = 30,
        @Query("category")
        category: String = CATEGORY_BUSINESS
    ): Observable<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getEntertainmentNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("category")
        category: String = CATEGORY_ENTERTAINMENT
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getHealthNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("category")
        category: String = CATEGORY_HEALTH
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getTechnologyNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("category")
        category: String = CATEGORY_TECHNOLOGY
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getScienceNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("category")
        category: String = CATEGORY_SCIENCE
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getSportNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("category")
        category: String = CATEGORY_SPORTS
    ): Response<NewsResponse>

}