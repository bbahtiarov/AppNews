package com.example.appnews.presentation.main.business

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appnews.data.model.NewsResponse
import com.example.appnews.data.repositories.NewsRepository
import com.example.appnews.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BusinessViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsResponse: NewsResponse? = null
    private val compositeDisposable = CompositeDisposable()


    init {
        getBusinessNews()
    }

    private fun getBusinessNews() {
        news.postValue(Resource.Loading())
        fetchBusinessNews()
    }

    private fun fetchBusinessNews() {
            compositeDisposable.add(newsRepository.getBusinessNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultResponse->
                    if(newsResponse == null) {
                        newsResponse = resultResponse
                    } else {
                        val oldArticles = newsResponse?.articles
                        val newArticles = resultResponse.articles
                        oldArticles?.addAll(newArticles)
                    }
                    news.value = Resource.Success(newsResponse ?: resultResponse)

                }, {
                    Resource.Error(it.message.toString(), null)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}