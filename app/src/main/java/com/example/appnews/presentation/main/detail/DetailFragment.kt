package com.example.appnews.presentation.main.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.appnews.R
import com.example.appnews.di.ContextModule
import com.example.appnews.di.DaggerAppComponent
import com.example.appnews.utils.factories.ViewModelProviderFactory
import com.example.appnews.utils.ext.injectViewModel
import com.example.appnews.utils.showSnackbar
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val args: DetailFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appComponent = DaggerAppComponent.builder().contextModule(ContextModule(requireContext())).build()
        viewModelProviderFactory = appComponent.getViewModelFactory()
        detailViewModel = injectViewModel(viewModelProviderFactory)

        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        like_btn.setOnClickListener {
            detailViewModel.saveArticle(article)
            showSnackbar(view, getString(R.string.save_article))
        }
    }
}