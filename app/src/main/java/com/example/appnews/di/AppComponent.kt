package com.example.appnews.di

import com.example.appnews.NewsApplication
import com.example.appnews.utils.factories.ViewModelProviderFactory
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<NewsApplication> {

    fun getViewModelFactory() : ViewModelProviderFactory

}