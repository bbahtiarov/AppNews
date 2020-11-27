package com.example.appnews.presentation.main.health

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.model.NewsResponse
import com.example.appnews.data.repositories.NewsRepository
import com.example.appnews.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class HealthViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsResponse: NewsResponse? = null

    init {
        getHealthNews()
    }

    private fun getHealthNews() = viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = newsRepository.getHealthNews()
        news.postValue(handleHealthNewsResponse(response))
    }

    private fun handleHealthNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(newsResponse == null) {
                    newsResponse = resultResponse
                } else {
                    val oldArticles = newsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(newsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}