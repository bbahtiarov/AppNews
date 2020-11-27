package com.example.appnews.di

import android.app.Application
import com.example.appnews.NewsApplication
import com.example.appnews.utils.factories.ViewModelProviderFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<NewsApplication> {

    fun getViewModelFactory() : ViewModelProviderFactory

}