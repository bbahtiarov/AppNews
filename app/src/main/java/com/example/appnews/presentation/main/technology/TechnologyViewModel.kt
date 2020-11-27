package com.example.appnews.presentation.main.technology

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.model.NewsResponse
import com.example.appnews.data.repositories.NewsRepository
import com.example.appnews.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class TechnologyViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsResponse: NewsResponse? = null

    init {
        getTechnologyNews()
    }

    private fun getTechnologyNews() = viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = newsRepository.getTechnologyNews()
        news.postValue(handleTechnologyNewsResponse(response))
    }

    private fun handleTechnologyNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
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