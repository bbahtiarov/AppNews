package com.example.appnews.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appnews.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): Long

    @Query("SELECT * FROM news_table")
    fun getAllNews(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}