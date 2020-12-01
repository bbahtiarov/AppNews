package com.example.appnews.data.repositories

import com.example.appnews.data.model.Article
import com.example.appnews.data.api.NewsClient
import com.example.appnews.data.room.NewsDatabase

class NewsRepository(private val db: NewsDatabase) {
    fun getBusinessNews() = NewsClient.api.getBusinessNews()
    suspend fun getEntertainmentNews() = NewsClient.api.getEntertainmentNews()
    suspend fun getHealthNews() = NewsClient.api.getHealthNews()
    suspend fun getTechnologyNews() = NewsClient.api.getTechnologyNews()
    suspend fun getSportNews() = NewsClient.api.getSportNews()
    suspend fun getScienceNews() = NewsClient.api.getScienceNews()

    suspend fun insertArticle(article: Article) = db.getNewsDao().insertArticle(article)
    fun getSavedArticles() = db.getNewsDao().getAllNews()
    suspend fun deleteArticle(article: Article) = db.getNewsDao().deleteArticle(article)
}