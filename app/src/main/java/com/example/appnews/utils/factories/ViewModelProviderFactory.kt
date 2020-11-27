package com.example.appnews.utils.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appnews.data.repositories.NewsRepository
import com.example.appnews.presentation.main.business.BusinessViewModel
import com.example.appnews.presentation.main.detail.DetailViewModel
import com.example.appnews.presentation.main.entertainment.EntertainmentViewModel
import com.example.appnews.presentation.main.health.HealthViewModel
import com.example.appnews.presentation.main.science.ScienceViewModel
import com.example.appnews.presentation.main.sport.SportViewModel
import com.example.appnews.presentation.main.technology.TechnologyViewModel
import com.example.appnews.presentation.navigationDrawer.bookmarks.BookmarksViewModel

class ViewModelProviderFactory (
    private val movieRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BusinessViewModel::class.java)){
            return BusinessViewModel(movieRepository) as T
        }
        if(modelClass.isAssignableFrom(EntertainmentViewModel::class.java)){
            return EntertainmentViewModel(movieRepository) as T
        }
        if(modelClass.isAssignableFrom(HealthViewModel::class.java)){
            return HealthViewModel(movieRepository) as T
        }
        if(modelClass.isAssignableFrom(ScienceViewModel::class.java)){
            return ScienceViewModel(movieRepository) as T
        }
        if(modelClass.isAssignableFrom(SportViewModel::class.java)){
            return SportViewModel(movieRepository) as T
        }
        if(modelClass.isAssignableFrom(TechnologyViewModel::class.java)){
            return TechnologyViewModel(movieRepository) as T
        }
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(movieRepository) as T
        }
        if(modelClass.isAssignableFrom(BookmarksViewModel::class.java)){
            return BookmarksViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}