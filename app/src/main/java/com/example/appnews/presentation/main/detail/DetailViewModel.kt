package com.example.appnews.presentation.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.model.Article
import com.example.appnews.data.repositories.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insertArticle(article)
    }
}