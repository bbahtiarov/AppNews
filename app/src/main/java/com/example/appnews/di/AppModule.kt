package com.example.appnews.di

import android.content.Context
import androidx.room.Room
import com.example.appnews.data.repositories.NewsRepository
import com.example.appnews.data.room.NewsDatabase
import com.example.appnews.utils.factories.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ContextModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideMovieDatabase (
     context: Context
    ) = Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "news_db").build()

    @Singleton
    @Provides
    fun provideNewsRepo(database: NewsDatabase) = NewsRepository(database)

    @Singleton
    @Provides
    fun provideBusinessFactory(
        newsRepository: NewsRepository
    ) = ViewModelProviderFactory(newsRepository)

}